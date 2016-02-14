package api.services.files;


import api.models.authentication.Authentication;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;

import java.io.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
public class FileTransferController {
    @Autowired FileRepository fr;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponseEntity uploadFile(HttpServletRequest request){

        try {
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
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (!item.isFormField()) {
                    String filename = item.getName();
                    // Process the input stream
                    OutputStream out = new FileOutputStream(filename);
                    IOUtils.copy(stream, out);
                    stream.close();
                    out.close();
                }
            }
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
