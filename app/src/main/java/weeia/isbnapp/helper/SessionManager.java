package weeia.isbnapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by Anna on 2017-04-27.
 */
public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences preferences;
    Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_TOKEN = "token";


    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setToken(String token) {
        editor.putString(KEY_TOKEN, token);

        editor.commit();

        Log.d(TAG, "User token saved!");
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
