package weeia.isbnapp.presenter;

/**
 * Created by Anna on 2017-04-22.
 */
public interface LogInPresenterInterface {
    void validateCredentials(String username, String password);

    void onDestroy();
}
