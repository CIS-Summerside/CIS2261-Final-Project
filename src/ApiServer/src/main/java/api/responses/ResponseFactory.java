package api.responses;

import org.springframework.http.HttpStatus;

/**
 * Created by Connor on 2016-02-06.
 */
public class ResponseFactory {

    public static <T> ApiResponseEntity<T> failResponse(T body){
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

    public static <T> ApiResponseEntity<T> okResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.OK);
    }

    public static <T> ApiResponseEntity<T> authErrorResponse(T body){
        return new ApiResponseEntity<T>(body, HttpStatus.UNAUTHORIZED);
    }

    public static ApiResponseEntity<String> unauthorizedResponse(){
        return new ApiResponseEntity<String>("Authentication Required", HttpStatus.UNAUTHORIZED);
    }
}
