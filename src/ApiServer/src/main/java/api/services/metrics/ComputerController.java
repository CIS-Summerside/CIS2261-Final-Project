package api.services.metrics;

import api.models.base.ApiResponse;
import api.models.data.Computer;
import api.models.data.User;
import api.models.errors.Info;
import api.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: ${FILE}
 * Author: compa
 * Created: 2016-02-03
 */
@RestController
@RequestMapping("/api/metrics/computer")
public class ComputerController {

    @Autowired
    ComputerRepository cr;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addComputer(@RequestBody Computer computer) {
        ApiResponse response;

        cr.save(computer);
        response = new ApiResponse(HttpStatus.OK.value(), new Info("Added Computer"));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
