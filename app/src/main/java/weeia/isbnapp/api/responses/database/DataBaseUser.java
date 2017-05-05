package weeia.isbnapp.api.responses.database;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Anna on 2017-04-22.
 */
public class DataBaseUser implements Serializable {
    private Integer id;
    private Integer ID;
    private String Username;
    private String Password;
    private Boolean IsActive;
    private Date createDate;
    private Integer RoleID;

    public String getPassword()
    {
        return this.Password;
    }

    public String getUserName()
    {

        return this.Username;
    }
}
