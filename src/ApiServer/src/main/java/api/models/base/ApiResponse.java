package api.models.base;

/**
 * Created by Connor on 2016-01-28.
 */
public class ApiResponse {
    private String version = "0.1";
    private Integer status = 0;
    private String terms = "http://www.google.com/terms";
    private Object data;

    public ApiResponse(Integer status, Object data){
        this.status = status;
        this.data = data;
    }

    public String getVersion(){
        return this.version;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTerms() {
        return terms;
    }

    public Object getData() {
        return data;
    }
}
