package weeia.isbnapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import weeia.isbnapp.api.OffersInterface;
import weeia.isbnapp.background.BackgroundService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getDataButton;
    private EditText lookForEditText ;
    private EditText temporaryEditText ;

    public static final String RECEIVE_JSON = "com.your.package.RECEIVE_JSON";

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(RECEIVE_JSON)) {
                OffersInterface offers = (OffersInterface) intent.getSerializableExtra("json");
                temporaryEditText.setText(offers.getOffers().get(0).toString());
            }
        }
    };
    LocalBroadcastManager bManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataButton = (Button)findViewById(R.id.my_button);
        temporaryEditText = (EditText)findViewById(R.id.my_edit);
        lookForEditText = (EditText)findViewById(R.id.my_look_for);
        getDataButton.setOnClickListener(this);

        bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVE_JSON);
        bManager.registerReceiver(bReceiver, intentFilter);
    }

    @Override
    public void onClick(View arg0) {

        Button b = (Button)findViewById(R.id.my_button);

       // b.setClickable(false);
        sendNetworkRequest(lookForEditText.getText().toString());
    }


    private void sendNetworkRequest(String phrase)
    {
        Intent intent = new Intent(this, BackgroundService.class);
        intent.putExtra("phrase", phrase);
        startService(intent);
    }
}
