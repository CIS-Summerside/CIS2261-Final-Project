package api.services;

import api.models.data.User;
import api.models.errors.Information;
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
    private UserRepository userRepository;

    /**
     * REST endpoint for getting information on user.
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable("id") final Integer id) {
        try {
            User user = userRepository.findOneByUserId(id);
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } catch (Exception ex){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * REST endpoint for adding a new user to the database.
     */
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
