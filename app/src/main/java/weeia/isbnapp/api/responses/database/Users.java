package weeia.isbnapp.api.responses.database;

import java.io.Serializable;
import java.util.List;
import weeia.isbnapp.api.model.User;

/**
 * Created by Anna on 2017-04-22.
 */
public class Users implements Serializable {
    private String title;
    private List<DataBaseUser> users;

    public List<DataBaseUser> getUsers() {
        return this.users;
    }

}
