package api.services.metrics;

import api.models.authentication.Authentication;
import api.responses.ApiResponse;
import api.models.data.UserAgent;
import api.models.errors.Info;
import api.repositories.UserAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@RestController
@RequestMapping("/api/metrics/agent")
public class UserAgentController extends Authentication{

    @Autowired
    UserAgentRepository uar;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addAgent(HttpServletRequest request, @RequestBody UserAgent agent) {
        ApiResponse response;

        if(super.getBasicAuth(request.getHeader("token"))) {
            uar.save(agent);
            response = new ApiResponse(HttpStatus.OK.value(), new Info("Added User Agent"));
        } else {
            response = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), new Info("Authentication Required"));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

