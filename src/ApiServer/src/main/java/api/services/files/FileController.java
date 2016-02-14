package api.services.files;

import api.models.authentication.Authentication;
import api.models.data.File;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Connor on 2016-02-07.
 */
@RestController
@RequestMapping("/api/files")
public class FileController extends Authentication{

    @Autowired FileRepository fr;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ApiResponseEntity getFileDetails(@PathVariable("id") final Long id) {
        File file = fr.findOne(id);

        if(file != null){
            return ResponseFactory.foundResponse(file);
        } else return ResponseFactory.notFoundResponse("No file found by ID");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResponseEntity getFileListing() {
        List<File> files = fr.findAllActiveAndViewable();

        if(files != null) {
            return ResponseFactory.okResponse(files);
        } else return ResponseFactory.notFoundResponse("No files found");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseEntity addFileDetails(HttpServletRequest request, @RequestBody File file){
        if(super.getBasicAuth(request.getHeader("token"))) {
            fr.save(file);
            return ResponseFactory.createdResponse("Added File Details");
        } else return ResponseFactory.unauthorizedResponse();
    }
}
