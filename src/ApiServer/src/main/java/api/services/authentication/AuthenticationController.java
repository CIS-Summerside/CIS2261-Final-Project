package api.services.authentication;

import api.responses.ApiResponse;
import api.models.data.Token;
import api.models.data.User;
import api.models.errors.Info;
import api.repositories.TokenRepository;
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
public class AuthenticationController {

    @Autowired
    private UserRepository ur;
    @Autowired
    private TokenRepository tr;

    /**
     * REST endpoint for logging in a user and returning a token.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginUser(@RequestBody User login) {
        ApiResponse response;
        User user = ur.findOneByUsername(login.getUsername());
        Token existingToken = tr.findOneByUserId(user.getId());

        try {
            if (user != null) {
                if(existingToken == null) {
                    String providedData = PasswordTools.sha256Hash(user.getPasswordSalt() + login.getPasswordHash());
                    String storedData = PasswordTools.sha256Hash(user.getPasswordSalt() + user.getPasswordHash());

                    if (providedData.equals(storedData)) {
                        Token token = new Token(user.getId());
                        tr.save(token);

                        response = new ApiResponse(HttpStatus.OK.value(), token);
                    } else {
                        response = new ApiResponse(HttpStatus.OK.value(), new Info("Incorrect Password"));
                    }
                } else{
                    response = new ApiResponse(HttpStatus.OK.value(), new Info("Already logged in"));
                }
            } else {
                response = new ApiResponse(HttpStatus.NOT_FOUND.value(), new Info("No user found by ID"));
            }
        } catch (Exception ex){
            response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Info("Failed to process Login"));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * REST endpoint for logging out a user
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object loginUser(@RequestHeader("Token") String token) {
        ApiResponse response;
        Token existingToken = tr.findOneByToken(token);
        tr.delete(existingToken);
        response = new ApiResponse(HttpStatus.OK.value(), new Info("Logged out"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
