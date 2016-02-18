package api.services.files;


import api.models.authentication.Authentication;
import api.models.data.Computer;
import api.models.data.File;
import api.models.data.Upload;
import api.models.data.User;
import api.repositories.ComputerRepository;
import api.repositories.FileRepository;
import api.repositories.UploadRepository;
import api.repositories.UserRepository;
import api.responses.ApiResponseEntity;
import api.responses.ResponseFactory;

import java.io.FileInputStream;


import api.tools.UrlTools;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.NumberUtils;

import static org.apache.commons.lang.NumberUtils.isNumber;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Created by Jordon
 */
@RestController
@RequestMapping("/api/files")
public class FileTransferController extends Authentication {
    @Autowired
    FileRepository fr;
    @Autowired
    ComputerRepository cr;
    @Autowired
    UploadRepository upr;
    @Autowired
    UserRepository ur;

    private static final String storeLoc = "Files/";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponseEntity uploadFile(HttpServletRequest request) {
        String token = request.getHeader("token");
        String computerHash = request.getHeader("cpt");

        try {
            if (super.getBasicAuth(token)) {
                File file = new File();
                User user = ur.findOneByUserId(super.getUserId(token));
                Computer computer = cr.findOneByIdentifierHash(computerHash);

                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                if (!isMultipart) return ResponseFactory.failResponse("Not a multipart request");

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload();
                // Parse the request
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {

                    FileItemStream item = iter.next();
                    InputStream stream = item.openStream();
                    String fieldName = item.getFieldName();

                    if (item.isFormField() && fieldName.equals("privacy")) {
                        String content = Streams.asString(stream);
                        System.out.println("File Access: " + content);
                        if(isNumber(content)) {
                            byte privacy = Byte.parseByte(content);

                            if ((privacy >= 0) && (privacy <= 1)) 
                                file.setFileAccess(privacy);
                            else 
                                file.setFileAccess((byte) 0);
                        } else file.setFileAccess((byte) 0);
                    }else if (item.isFormField() && fieldName.equals("duration")) {
                        String content = Streams.asString(stream);
                        System.out.println("Duration: " + content);
                        if(isNumber(content)) {
                            int duration = Integer.parseInt(content);
                            file.setExpirationTime(new Date(System.currentTimeMillis()+(duration*60*1000)));
                        } else 
                            file.setExpirationTime(new Date(System.currentTimeMillis()+(5*60*1000)));
                    }else if (!item.isFormField() && fieldName.equals("file")) {
                        String randCode = UrlTools.getBase64(file.getOriginalName());
                        file.setOriginalName(item.getName());
                        file.setStoredName(randCode + ".store");
                        file.setDownloadCode(randCode);
                        file.setFileStatus((byte) 0);

                        // Process the input stream
                        OutputStream out = new FileOutputStream(storeLoc + file.getStoredName());
                        IOUtils.copy(stream, out);
                        stream.close();
                        out.close();

                        file.setFileSize(new java.io.File(storeLoc + file.getStoredName()).length());
                    }
                }
                fr.save(file);

                if (computer != null && user != null) {
                    Upload uploadDetails = new Upload();
                    uploadDetails.setFileId(file);
                    uploadDetails.setComputerId(computer);
                    uploadDetails.setUserId(user);
                    upr.save(uploadDetails);
                }

                return ResponseFactory.okResponse(file);
            } else return ResponseFactory.unauthorizedResponse();
        } catch (FileUploadException e) {
            return ResponseFactory.failResponse("Upload File Failed");
        } catch (IOException e) {
            //System.out.println(e.getMessage());
            return ResponseFactory.failResponse("Server Failed To Process File");
        }
    }

    @RequestMapping(value = "/download/{code}", method = RequestMethod.GET)
    public ApiResponseEntity getFile(@PathVariable("code") String code, HttpServletResponse response) {
        try {
            File file = fr.findOneByDownloadCode(code);
            if (file != null) {
                if ((file.getFileStatus() == 0) && (file.getFileAccess() == 0)) {
                    response.setContentType("application/force-download");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getOriginalName() + "\"");
                    // get your file as InputStream
                    java.io.File download = new java.io.File(storeLoc + file.getStoredName());
                    InputStream is = new FileInputStream(download);

                    // copy it to response's OutputStream
                    org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                    response.flushBuffer();
                } else {
                    return ResponseFactory.authErrorResponse("File not available for download");
                }
            } else {
                return ResponseFactory.notFoundResponse("File not found");
            }
        } catch (IOException ex) {
            System.out.println("Error writing file to output stream. Filename was '{}'");
            return ResponseFactory.failResponse("Error processing file for download");
        }
        return ResponseFactory.okResponse("File downloading");
    }
}
