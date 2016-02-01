package api.models.errors;

/**
 * Created by Connor on 2016-01-28.
 */
public class Info {
    private String message;

    public Info(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return this.message;
    }
}
