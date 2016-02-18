package api.services.metrics;

import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.models.authentication.Authentication;
import api.models.data.Computer;
import api.repositories.ComputerRepository;
import api.responses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@RestController
@RequestMapping("/api/metrics/computer")
public class ComputerController extends Authentication {

    @Autowired
    ComputerRepository cr;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseEntity addComputer(HttpServletRequest request, @RequestBody Computer computer) {
        try {
            //if (super.getBasicAuth(request.getHeader("token"))) {
                String hashedComputer = api.tools.PasswordTools.sha256Hash(computer.getIdentifierRaw());
                computer.setIdentifierHash(hashedComputer);
                if(cr.findOneByIdentifierHash(hashedComputer) != null){
                    return ResponseFactory.foundResponse("Found that computer");
                } else {
                    cr.save(computer);
                    return ResponseFactory.createdResponse("Added Computer");
                }
           // } else return ResponseFactory.unauthorizedResponse();
        } catch (Exception ex) {
           return ResponseFactory.failResponse(ex.getMessage());
        }
    }
}