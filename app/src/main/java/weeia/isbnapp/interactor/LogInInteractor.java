package weeia.isbnapp.interactor;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weeia.isbnapp.api.DataBaseInterface;
import weeia.isbnapp.api.model.LoginRequest;
import weeia.isbnapp.api.model.LoginResponse;
import weeia.isbnapp.helper.PasswordHasher;

/**
 * Created by Anna on 2017-04-22.
 */
public class LogInInteractor implements LogInInteractorInterface {
    @Override
    public void login(final String username, String password, final OnLoginFinishedListener listener) {
        try {
            password = PasswordHasher.SHA1(password);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException $e){
            Log.d("Password Hassing", "bad password hasing");
        }

        LoginRequest loginRequest = new LoginRequest(username, password);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://isbnprojectapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DataBaseInterface client = retrofit.create(DataBaseInterface.class);
        Call<LoginResponse> call = client.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {

            private LogInInteractorInterface.OnLoginFinishedListener listener;

            private Callback<LoginResponse> init(LogInInteractorInterface.OnLoginFinishedListener listener) {
                this.listener = listener;
                return  this;
            }

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse login = response.body();

                if (!login.getSuccess()) {
                   listener.onPasswordError();
                } else {
                    listener.onSuccess(login.getToken());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onFailure();
            }
        }.init(listener));
    }
}
