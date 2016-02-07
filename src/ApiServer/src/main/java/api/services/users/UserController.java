package api.services.users;

import api.responses.BaseResponse;
import api.models.data.User;
import api.repositories.UserRepository;
import api.responses.ApiResponseEntity;
import api.responses.ResponseFactory;
import api.tools.PasswordTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Connor on 2016-01-28.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository ur;

    /**
     * REST endpoint for getting information on user.
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ApiResponseEntity<BaseResponse> getUser(@PathVariable("id") final Long id) {
        User user = ur.findOneByUserId(id);

        if(user != null){
            return ResponseFactory.foundResponse(new BaseResponse(user));
        } else {
            return ResponseFactory.notFoundResponse(new BaseResponse("No user found by ID"));
        }
    }

    /**
     * REST endpoint for adding a new user to the database.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        BaseResponse response;

        if(ur.findOneByUsername(user.getUsername()) == null){
            user.setPasswordSalt(PasswordTools.generateSalt());
            ur.save(user);
            response = new BaseResponse(user);
            return ResponseFactory.createdResponse(response);
        } else {
            return ResponseFactory.foundResponse(new BaseResponse("User Already Exists"));
        }
    }
}
