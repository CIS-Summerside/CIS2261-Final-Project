package api.services;

import api.models.data.User;
import api.models.errors.GeneralError;
import api.models.errors.Information;
import api.repositories.UserRepository;
import api.tools.PasswordTools;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    private UserRepository userRepository;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable("id") final Integer id) {
        try {
            User user = userRepository.findOneById(id);
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } catch (Exception ex){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        try {
            user.setPasswordSalt(PasswordTools.generateSalt());
            userRepository.save(user);

            Information response = new Information(HttpStatus.CREATED.value(), "User was created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}