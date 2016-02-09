package api.services.metrics;

import api.models.authentication.Authentication;
import api.responses.ApiResponseEntity;
import api.responses.BaseResponse;
import api.models.data.UserAgent;
import api.repositories.UserAgentRepository;
import api.responses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired UserAgentRepository uar;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseEntity addAgent(HttpServletRequest request, @RequestBody UserAgent agent) {
        if(super.getBasicAuth(request.getHeader("token"))) {
            uar.save(agent);
            return ResponseFactory.createdResponse("Added User Agent");
        } else return ResponseFactory.unauthorizedResponse();
    }
}

