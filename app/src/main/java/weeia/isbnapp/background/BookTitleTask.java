package weeia.isbnapp.background;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weeia.isbnapp.api.GoogleBooksInterface;
import weeia.isbnapp.api.Info;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public class BookTitleTask extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
       String isbn = params[0];
        GoogleBooksInterface service = createGoogleBooksAPI();
        Call<Info> infoCallback = service.getTitle(prepareQuery(isbn));
        try {
            Response<Info> result = infoCallback.execute();
            if (result.isSuccessful()) {
                Info info = result.body();
                if (info.totalItems > 0) {
                    return info.items.get(0).volumeInfo.title;
                } else {
                    throw new Exception("brak danego tytułu");
                }
            } else {
                throw new Exception("brak danego tytułu");
            }

        } catch (IOException e) {
            Log.i("Retrofit", "Failure");
            return null;
        } catch (Exception e) {
            Log.i("Retrofit", "Brak tytułu");
            return null;
        }
    }
    private String prepareQuery(String isbn)
    {
        return "isbn:"+isbn;
    }
    private GoogleBooksInterface createGoogleBooksAPI() {
        GoogleBooksInterface googleApi;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoogleBooksInterface.BASE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        googleApi = retrofit.create(GoogleBooksInterface.class);
        return googleApi;
    }
}
