package weeia.isbnapp.api.model;

/**
 * Created by Anna on 2017-05-04.
 */

public class LoginResponse {

     private boolean success;
     private String message;
     private String token;

    public LoginResponse(boolean success, String message, String token)
    {
        this.success = success;
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public String getToken()
    {
        return this.token;
    }

    public void setMessage(String message) {
         this.message = message;
    }

    public void setSuccess(boolean success) {
         this.success = success;
    }

    public void setToken(String token)
    {
         this.token = token;
    }
}
