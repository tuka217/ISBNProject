package weeia.isbnapp.book.offers.allegro.background;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weeia.isbnapp.book.offers.allegro.api.AllegroInterface;
import weeia.isbnapp.book.offers.allegro.api.Offers;

/**
 * Created by Anna on 2017-04-07.
 */
public class BackgroundService extends IntentService {

    private ResultReceiver resultReceiver;
    public static final String RECEIVE_JSON = "com.your.package.RECEIVE_JSON";
    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(" https://allegroapi.io")
                .addConverterFactory(GsonConverterFactory.create())
        ;

        Retrofit retrofit = builder.build();

        AllegroInterface client = retrofit.create(AllegroInterface.class);

        Call<Offers> call = client.booksByPhrase(intent.getStringExtra("phrase"), "PL", "7");

        try {
            Response<Offers> result = call.execute();

            Intent RTReturn = new Intent(RECEIVE_JSON);
            RTReturn.putExtra("json", result.body());

            LocalBroadcastManager.getInstance(this).sendBroadcast(RTReturn);

        } catch (IOException e) {
            Log.i("Retrofit", "Failure");
        }
    }
}
