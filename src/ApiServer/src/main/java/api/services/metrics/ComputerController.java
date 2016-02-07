package api.services.metrics;

import api.responses.ApiResponse;
import api.models.authentication.Authentication;
import api.models.data.Computer;
import api.models.errors.Info;
import api.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@RestController
@RequestMapping("/api/metrics/computer")
public class ComputerController extends Authentication{

    @Autowired
    ComputerRepository cr;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addComputer(HttpServletRequest request, @RequestBody Computer computer) {
        ApiResponse response;

        if(super.getBasicAuth(request.getHeader("token"))) {
            cr.save(computer);
            response = new ApiResponse(HttpStatus.OK.value(), new Info("Added Computer"));
        } else {
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), new Info("Authentication Required"));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
