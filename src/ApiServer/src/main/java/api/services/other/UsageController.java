package api.services.other;

import api.models.data.User;
import api.responses.ApiResponseEntity;
import api.responses.ResponseFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-17
 */
@RestController
@RequestMapping("/api")
public class UsageController {

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public String getTerms() {
        return "Please don't do bad things.";
    }
}
