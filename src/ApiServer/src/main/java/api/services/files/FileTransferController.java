package api.services.files;


import api.models.authentication.Authentication;
import api.models.data.Computer;
import api.models.data.File;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;
import java.io.FileInputStream;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 * Created by Jordon
 */
@RestController
@RequestMapping("/api/files")
public class FileTransferController extends Authentication {
    @Autowired FileRepository fr;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponseEntity uploadFile(HttpServletRequest request){
        String token = request.getHeader("token");
        try {
            if(super.getBasicAuth(token)) {
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                if (!isMultipart) return ResponseFactory.failResponse("Not a multipart request");

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload();
                // Parse the request
                FileItemIterator iter = upload.getItemIterator(request);

                while (iter.hasNext()) {
                    File file = new File();
                    FileItemStream item = iter.next();
                    InputStream stream = item.openStream();
                    String fieldName = item.getFieldName();

                    if (!item.isFormField() && fieldName.equals("file")) {
                        String randCode = api.tools.Base62.getBase64(file.getOriginalName());
                        file.setOriginalName(item.getName());
                        file.setStoredName(randCode + ".store");
                        file.setDownloadCode(randCode);
                        file.setFileAccess((byte) 1);
                        file.setFileStatus((byte) 1);

                        // Process the input stream
                        OutputStream out = new FileOutputStream(file.getStoredName());
                        IOUtils.copy(stream, out);
                        stream.close();
                        out.close();

                        file.setFileSize(new java.io.File(file.getStoredName()).length());

                        fr.save(file);
                    }
                }
            } else return ResponseFactory.unauthorizedResponse();
        } catch (FileUploadException e) {
            return ResponseFactory.failResponse("Upload File Failed");
        } catch (IOException e) {
            return ResponseFactory.failResponse("Server Failed To Process File");
        }

        return ResponseFactory.okResponse("Uploaded");
    }
    
    @RequestMapping(value = "/download/{code}", method = RequestMethod.GET)
    public ApiResponseEntity getFile(@PathVariable("code") String code, HttpServletResponse response) {
        try {
            File file = fr.findOneByDownloadCode(code);
            if(file != null) {
                if(file.getFileStatus() == 2) {
                    response.setContentType("application/force-download");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getOriginalName() + "\"");
                    // get your file as InputStream
                    java.io.File download = new java.io.File(file.getStoredName());
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
