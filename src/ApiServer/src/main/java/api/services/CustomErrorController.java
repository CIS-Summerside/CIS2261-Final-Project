package api.services;

import api.model.Errors;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Connor on 2016-01-27.
 */
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    Errors error(HttpServletRequest request, HttpServletResponse response) {
        return new Errors(response.getStatus());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
