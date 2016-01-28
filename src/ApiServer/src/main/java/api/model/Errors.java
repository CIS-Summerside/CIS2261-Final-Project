package api.model;

/**
 * Created by Connor on 2016-01-27.
 */
public class Errors {
    private Integer errorCode;

    public Errors(Integer errorCode){
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
