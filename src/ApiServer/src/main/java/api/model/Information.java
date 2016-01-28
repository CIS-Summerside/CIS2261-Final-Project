package api.model;

/**
 * Created by Connor on 2016-01-28.
 */
public class Information {

    private Integer statusCode;
    private String message;

    public Information(Integer status, String msg){
        this.statusCode = status;
        this.message = msg;
    }

    public Integer getStatusCode(){
        return this.statusCode;
    }

    public String getMessage(){
        return this.message;
    }
}
