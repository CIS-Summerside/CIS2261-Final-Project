package api.models.errors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Connor on 2016-01-27.
 */
public class GeneralError {
    public String error;
    public String timeStamp;

    public GeneralError(Map<String, Object> errorAttributes) {
        this.error = errorAttributes.get("error").toString();
        this.timeStamp = LocalDateTime.now().toString();
    }

    public GeneralError(Integer status, String message){
        this.error = "";
        this.timeStamp = LocalDateTime.now().toString();
    }

    public GeneralError(){

    }
}
