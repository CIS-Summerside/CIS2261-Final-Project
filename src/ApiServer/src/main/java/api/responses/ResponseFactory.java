package api.responses;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * Created by Connor on 2016-02-06.
 */
public class ResponseFactory {

    public static <T> ApiResponseEntity<T> failResponce(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.BAD_REQUEST);
    }

    public static <T> ApiResponseEntity<T> createdResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.CREATED);
    }

    public static <T> ApiResponseEntity<T> foundResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.FOUND);
    }

    public static <T> ApiResponseEntity<T> notFoundResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.NOT_FOUND);
    }

    public static <T> ApiResponseEntity<T> unauthorizedResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.UNAUTHORIZED);
    }
}
