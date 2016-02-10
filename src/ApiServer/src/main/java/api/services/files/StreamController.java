package api.services.files;

import api.models.authentication.Authentication;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;
import java.io.BufferedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 * Created by Connor on 2016-02-07.
 */
@RestController
@RequestMapping("/api/files")
public class StreamController extends Authentication{

    @Autowired FileRepository fr;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    //public String uploadFile(HttpServletRequest request, @RequestBody File file){
    public String uploadFile(HttpServletRequest request){
        
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            
            ServletFileUpload upload = new ServletFileUpload();
            
            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    System.out.println("Form field " + name + " with value "
                            + Streams.asString(stream) + " detected.");
                } else {
                    System.out.println("File field " + name + " with file name "
                            + item.getName() + " detected.");
                    String fileName = item.getName();
                    
                    //Process the input stream
                    File uploadedFile = new File("/"+item.getName());

                    System.out.println(uploadedFile.getAbsolutePath());

                    FileOutputStream fileOut = new FileOutputStream(uploadedFile);
                    BufferedOutputStream bufferOut= new BufferedOutputStream (fileOut);
                    BufferedInputStream bufferIn= new BufferedInputStream(stream);
                    
                    int aByte;

                    while ((aByte=bufferIn.read()) != -1)
                    {
                        bufferOut.write(aByte);
                    }

                    bufferOut.close();
                    bufferIn.close();
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Success?";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadFile(HttpServletRequest request, @RequestBody File file){
        //get the id from the request
        
        //find the filepath that corresponds to the id
        
        //serve the file
        return "Success?";
    }
}
