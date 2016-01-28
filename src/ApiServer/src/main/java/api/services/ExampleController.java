package api.services;

import api.repositories.TestRepository;
import api.models.errors.GeneralError;
import api.models.errors.Information;
import api.models.data.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExampleController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/jpaAdd")
    public Object addTest(HttpServletRequest request) {
        try {
            if (request.getParameter("num") != null) {
                Integer newNum = Integer.parseInt(request.getParameter("num"));
                testRepository.save(new Test(newNum));
                return new Information(200, "Added to database");
            } else {
                return new GeneralError(204, "No repositories supplied");
            }
        } catch (Exception ex){
            return new GeneralError(400, ex.toString());
        }
    }

    @RequestMapping("/jpaGet")
    public Object getTest(HttpServletRequest request) {
        try {
            if (request.getParameter("id") != null) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                return testRepository.findOneById(id);
            } else {
                return new GeneralError(204, "ID returned no results");
            }
        } catch (Exception ex){
            return new GeneralError(400, ex.toString());
        }
    }
}