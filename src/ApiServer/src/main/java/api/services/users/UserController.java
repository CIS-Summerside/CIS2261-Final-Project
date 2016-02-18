package api.services.users;

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

    @Autowired private UserRepository ur;

    /**
     * REST endpoint for getting information on user.
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ApiResponseEntity getUser(@PathVariable("id") final Long id) {
        User user = ur.findOneByUserId(id);

        if(user != null){
            return ResponseFactory.foundResponse(user);
        } else {
            return ResponseFactory.notFoundResponse("No user found by ID");
        }
    }

    /**
     * REST endpoint for adding a new user to the database.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseEntity addUser(@RequestBody User user) {
        if(ur.findOneByUsername(user.getUsername()) == null){
            user.setPasswordSalt(PasswordTools.generateSalt());
            ur.save(user);
            return ResponseFactory.createdResponse(user);
        } else {
            return ResponseFactory.foundResponse("User Already Exists");
        }
    }
}
