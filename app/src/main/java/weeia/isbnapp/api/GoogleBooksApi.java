package weeia.isbnapp.api;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weeia.isbnapp.MainActivity;
import weeia.isbnapp.background.BookTitleTask;

/**
 * Created by MATEUSZ on 20.04.2017.
 */

public class GoogleBooksApi  {

    public  String GetBookTitleByISBN(String isbn) {
        BookTitleTask task = new BookTitleTask();
        String title = null;
        try {
            title = task.execute(isbn).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return title;

    }
}
