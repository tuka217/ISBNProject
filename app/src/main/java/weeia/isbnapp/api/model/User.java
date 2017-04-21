package weeia.isbnapp.api.model;

import java.util.Date;

/**
 * Created by Anna on 2017-04-21.
 */
public class User {

    private Integer id;
    private String userName;
    private String password;
    private Boolean isActive;
    private Date createdAt;
    private Integer roleID;

    public User(String userName, String password, Integer roleID) {
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
        this.createdAt = new Date();
        this.isActive = true;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.roleID = 1;
        this.createdAt = new Date();
        this.isActive = true;
    }

    public Integer getId()
    {
        return this.id;
    }
}
