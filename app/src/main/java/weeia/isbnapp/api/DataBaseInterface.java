package weeia.isbnapp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import weeia.isbnapp.api.model.LoginRequest;
import weeia.isbnapp.api.model.LoginResponse;
import weeia.isbnapp.api.model.User;
import weeia.isbnapp.api.responses.database.Users;

/**
 * Created by Anna on 2017-04-21.
 */
public interface DataBaseInterface {

    @Headers("Content-Type: application/json")
    @POST("user/create")
    Call<User> createAccount(@Body User user);

    @Headers("Accept: application/json")
    @GET("users")
    Call<Users> getUsers();

    @Headers("Content-Type: application/json")
    @POST("user/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
