package api.services.files;

import api.models.data.File;
import api.repositories.FileRepository;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.responses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Connor on 2016-02-07.
 */
@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired FileRepository fr;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ApiResponseEntity<BaseResponse> getFileDetails(@PathVariable("id") final Long id) {
        File file = fr.findOne(id);

        if(file != null){
            return ResponseFactory.foundResponse(new BaseResponse(file));
        } else {
            return ResponseFactory.notFoundResponse(new BaseResponse("No user found by ID"));
        }
    }
}
