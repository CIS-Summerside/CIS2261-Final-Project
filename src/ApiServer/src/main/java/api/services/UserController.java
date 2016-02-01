package api.services;

import api.models.base.ApiResponse;
import api.models.data.User;
import api.models.errors.Info;
import api.repositories.UserRepository;
import api.tools.PasswordTools;
import org.springframework.beans.factory.annotation.Autowired;
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
        Object response;
        HttpStatus statusCode;
        User user = ur.findOneByUserId(id);

        if(user != null){
            statusCode = HttpStatus.OK;
            response =  new ApiResponse(statusCode.value(), user);
        } else {
            Info info = new Info("No user found by ID");
            statusCode = HttpStatus.NOT_FOUND;
            response = new ApiResponse(statusCode.value(), info);
        }

        return new ResponseEntity<>(response, statusCode);
    }

    /**
     * REST endpoint for adding a new user to the database.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        Object response;
        try {
            user.setPasswordSalt(PasswordTools.generateSalt());
            ur.save(user);

            response =  new ApiResponse(HttpStatus.CREATED.value(), ur.findOne(user.getId()));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex){
            response = new Info("Failed to create user");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
