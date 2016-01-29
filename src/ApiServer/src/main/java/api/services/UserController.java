package api.services;

import api.models.data.User;
import api.models.errors.GeneralError;
import api.models.errors.Information;
import api.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
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
            return userRepository.findOneById(id);
        } catch (Exception ex){
            return new GeneralError(400, ex.toString());
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new Information(200, "Added to DB");
        } catch (Exception ex){
            return new GeneralError(400, ex.toString());
        }
    }
}
