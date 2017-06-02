package weeia.isbnapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import weeia.isbnapp.presenter.RegisterPresenter;

/**
 * Created by Anna on 2017-04-18.
 */
public class RegisterActivity extends AppCompatActivity implements  RegisterViewInterface, View.OnClickListener{

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private RegisterPresenter presenter;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.btnRegister).setOnClickListener(this);

        presenter = new RegisterPresenter(this);

        Button login = (Button) findViewById(R.id.btnLinkToLoginScreen);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void makeWrongDataToast() {
        Toast.makeText(RegisterActivity.this, "Ops! Something went wrong with passed data. Try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeSuccessToast() {
        Toast.makeText(RegisterActivity.this, "You were registered successfully.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}