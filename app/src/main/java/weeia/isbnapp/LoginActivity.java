package weeia.isbnapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import weeia.isbnapp.activities.MainActivity;
import weeia.isbnapp.helper.SessionManager;
import weeia.isbnapp.presenter.LogInPresenter;
import weeia.isbnapp.presenter.LogInPresenterInterface;

/**
 * Created by Anna on 2017-04-22.
 */
public class LoginActivity  extends Activity implements LogInViewInterface, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LogInPresenterInterface presenter;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        findViewById(R.id.btnLogin).setOnClickListener(this);

        presenter = new LogInPresenter(this);
        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Button register = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("Invalid user name");
    }

    @Override
    public void setPasswordError() {
        password.setError("Invalid password");
    }

    @Override
    public void makeWrongDataToast() {
        Toast.makeText(LoginActivity.this, "Ops! Something went wrong with passed data. Try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeSuccessToast(String token) {
        Toast.makeText(LoginActivity.this, "You were login successfully.", Toast.LENGTH_SHORT).show();
        // Create login session
        session.setLogin(true);
        session.setToken(token);

        // Launch main activity
        Intent intent = new Intent(LoginActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
