package weeia.isbnapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import weeia.isbnapp.client.GoodreadsClient;
import weeia.isbnapp.model.GoodreadsResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String API_BASE_URL = "https://www.goodreads.com/book/";
    private Button getDataButton;
    private EditText lookForEditText ;
    private EditText temporaryEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataButton = (Button)findViewById(R.id.my_button);
        temporaryEditText = (EditText)findViewById(R.id.my_edit);
        lookForEditText = (EditText)findViewById(R.id.my_look_for);
        getDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            SimpleXmlConverterFactory.create()
                    );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        GoodreadsClient client =  retrofit.create(GoodreadsClient.class);

        //"0441172717"
        Call<GoodreadsResponse> call = client.bookByISBN(lookForEditText.getText().toString(), "ylU5p7gcPnQEnLvhJlWYQ", "xml");

        call.enqueue(new Callback<GoodreadsResponse>() {
            @Override
            public void onResponse(Call<GoodreadsResponse> call, Response<GoodreadsResponse> response) {
                dialog.dismiss();
                GoodreadsResponse goodreadsResponse = response.body();
                temporaryEditText.setText(goodreadsResponse.getBook().getDescription());
            }

            @Override
            public void onFailure(Call<GoodreadsResponse> call, Throwable t) {
                dialog.dismiss();
                temporaryEditText.setText("Error while receiving data");
            }
        });
    }
}
