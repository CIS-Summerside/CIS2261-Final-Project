package api.services;

import api.data.TestRepository;
import api.model.HelloWorld;
import api.model.Information;
import api.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExampleController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/")
    public HelloWorld hello(@RequestParam(value="name", defaultValue="World") String name) {
        return new HelloWorld(1, "Hello world!");
    }

    @RequestMapping("/jpaAdd")
    public Information addTest(HttpServletRequest request) {
        try {
            if (request.getParameter("num") != null) {
                Integer newNum = Integer.parseInt(request.getParameter("num"));
                testRepository.save(new Test(newNum));
                return new Information(200, "Added to database");
            } else {
                return new Information(200, "Nothing to add");
            }
        } catch (Exception ex){
            return new Information(400, ex.getMessage());
        }
    }
}