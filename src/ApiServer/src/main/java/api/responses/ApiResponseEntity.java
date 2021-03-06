package api.responses;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Connor on 2016-02-06.
 */
public class ApiResponseEntity<T> extends ResponseEntity<BaseResponse> {

    public ApiResponseEntity(T body, HttpStatus status){
        super(new BaseResponse(body), status);
    }
}
