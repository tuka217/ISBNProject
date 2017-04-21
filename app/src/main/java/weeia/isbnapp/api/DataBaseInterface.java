package weeia.isbnapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import weeia.isbnapp.api.model.User;

/**
 * Created by Anna on 2017-04-21.
 */
public interface DataBaseInterface {

    @POST("user/create")
    Call<User> createAccount(@Body User user);
}
