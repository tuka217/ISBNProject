package weeia.isbnapp.api.model;

/**
 * Created by Anna on 2017-05-05.
 */

public class LoginRequest
{
    private String Username;
    private String Password;

    public LoginRequest(String userName, String password) {
        this.Username = userName;
        this.Password = password;
    }
    public String getPassword() {
        return this.Password;
    }

    public String getUsername()
    {
        return this.Username;
    }

    public void setUsername(String userName) {
        this.Username = userName;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

}
