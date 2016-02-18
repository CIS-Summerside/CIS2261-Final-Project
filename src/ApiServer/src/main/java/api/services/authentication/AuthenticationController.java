package api.services.authentication;

import api.responses.ApiResponseEntity;
import api.models.data.Token;
import api.models.data.User;
import api.repositories.TokenRepository;
import api.repositories.UserRepository;
import api.responses.ResponseFactory;
import api.tools.PasswordTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Connor on 2016-01-28.
 */
@RestController
@RequestMapping("/api/user")
public class AuthenticationController {

    @Autowired private UserRepository ur;
    @Autowired private TokenRepository tr;

    /**
     * REST endpoint for logging in a user and returning a token.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponseEntity loginUser(@RequestBody User login) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = ur.findOneByUsername(login.getUsername());

        if (user != null){
            Token existingToken = tr.findOneByUser(user);
            if(existingToken == null){
                String providedData = PasswordTools.sha256Hash(user.getPasswordSalt() + login.getPasswordHash());
                String storedData = PasswordTools.sha256Hash(user.getPasswordSalt() + user.getPasswordHash());

                if (providedData.equals(storedData)) {
                    Token token = new Token(user);
                    tr.save(token);

                    return ResponseFactory.okResponse(token);
                } else {
                    return ResponseFactory.authErrorResponse("Incorrect Password");
                }
            } else{
                return ResponseFactory.foundResponse("Already logged in");
            }
        } else {
            return ResponseFactory.notFoundResponse("User Not Found");
        }
    }

    /**
     * REST endpoint for logging out a user
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object loginUser(@RequestHeader("Token") String token) {
        Token existingToken = tr.findOneByToken(token);
        tr.delete(existingToken);
        return ResponseFactory.okResponse("Logged out");
    }
}
