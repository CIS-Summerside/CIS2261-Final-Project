package api.responses;

/**
 * Created by Connor on 2016-01-28.
 */
public class BaseResponse {
    private String version = "0.1";
    private String terms = "http://www.google.com/terms";
    private Object data;

    public BaseResponse(Object data){
        this.data = data;
    }

    public BaseResponse(){

    }

    public String getVersion(){
        return this.version;
    }

    public String getTerms() {
        return terms;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
