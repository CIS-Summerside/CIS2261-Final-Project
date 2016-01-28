package api.models.errors;

import api.models.base.ApiBase;

/**
 * Created by Connor on 2016-01-28.
 */
public class Information extends ApiBase{

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
