package weeia.isbnapp.interactor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weeia.isbnapp.api.DataBaseInterface;
import weeia.isbnapp.api.model.User;

/**
 * Created by Anna on 2017-04-18.
 */
public class RegisterInteractor implements RegisterInteractorInterface {

    @Override
    public void register(String username, String password, OnLoginFinishedListener listener) {

        User user = new User(username, password);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://isbnprojectapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DataBaseInterface client = retrofit.create(DataBaseInterface.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {

            private OnLoginFinishedListener listener;

            private Callback<User> init(OnLoginFinishedListener listener) {
                this.listener = listener;
                return  this;
            }

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError();
            }
        }.init(listener));
    }
}
