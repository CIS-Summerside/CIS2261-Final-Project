package api.services.files;


import api.models.authentication.Authentication;
import api.models.data.Computer;
import api.models.data.File;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            if(super.getBasicAuth(request.getHeader("token"))) {
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                if (!isMultipart) {
                    // Inform user about invalid request
                    return ResponseFactory.failResponse("Not a multipart request");
                }

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
                        file.setOriginalName(item.getName());
                        file.setStoredName(item.getName());
                        file.setDownloadCode("X1J5FS");
                        file.setFileSize((long) 1000002);
                        file.setFileAccess((byte) 1);
                        file.setFileStatus((byte) 1);

                        // Process the input stream
                        OutputStream out = new FileOutputStream(file.getOriginalName());
                        IOUtils.copy(stream, out);
                        stream.close();
                        out.close();

                        fr.save(file);
                    }
                }
            } else return ResponseFactory.unauthorizedResponse();
        } catch (FileUploadException e) {
            return ResponseFactory.failResponse("Upload File Failed");
        } catch (IOException e) {
            return ResponseFactory.failResponse("Server IO Error");
        }

        return ResponseFactory.okResponse("Uploaded");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadFile(HttpServletRequest request, @RequestBody File file){
        //get the id from the request

        //find the filepath that corresponds to the id

        //serve the file
        return "Success?";
    }
}
