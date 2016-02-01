package api.models.errors;

import api.models.base.ApiResponse;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Connor on 2016-01-27.
 */
public class GeneralError {
    public Integer status;
    public String error;
    public String timeStamp;

    public GeneralError(Integer status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = errorAttributes.get("error").toString();
        this.timeStamp = LocalDateTime.now().toString();
    }

    public GeneralError(Integer status, String message){
        this.status = status;
        this.error = "";
        this.timeStamp = LocalDateTime.now().toString();
    }

    public GeneralError(){

    }
}
