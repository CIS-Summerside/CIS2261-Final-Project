package api.responses;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Connor on 2016-02-06.
 */
public class ApiResponseEntity<T> extends ResponseEntity<T> {

    public ApiResponseEntity(T body, HttpStatus status){
        super(body, status);
    }
}
