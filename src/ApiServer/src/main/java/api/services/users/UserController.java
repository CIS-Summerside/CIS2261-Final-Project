package api.services.users;

import api.models.base.ApiResponse;
import api.models.data.User;
import api.models.errors.Info;
import api.repositories.UserRepository;
import api.tools.PasswordTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Object getUser(@PathVariable("id") final Long id) {
        ApiResponse response;
        User user = ur.findOneByUserId(id);

        if(user != null){
            response =  new ApiResponse(HttpStatus.OK.value(), user);
        } else {
            response = new ApiResponse(HttpStatus.NOT_FOUND.value(), new Info("No user found by ID"));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * REST endpoint for adding a new user to the database.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        ApiResponse response;

        try {
            if(ur.findOneByUsername(user.getUsername()) == null){
                response = saveUser(user);
            } else {
                response = userExists();
            }
        } catch (Exception ex){
            response = userCreationFailed();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ApiResponse saveUser(User user){
        user.setPasswordSalt(PasswordTools.generateSalt());
        ur.save(user);
        HttpStatus statusCode = HttpStatus.CREATED;
        return new ApiResponse(statusCode.value(), user);
    }

    private ApiResponse userExists(){
        HttpStatus statusCode = HttpStatus.CONFLICT;
        return new ApiResponse(statusCode.value(), new Info("User Already Exists"));
    }

    private ApiResponse userCreationFailed(){
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ApiResponse(statusCode.value(), new Info("Failed To Add User"));
    }
}
