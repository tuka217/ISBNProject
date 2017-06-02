package weeia.isbnapp.presenter;

/**
 * Created by Anna on 2017-04-21.
 */

public interface RegisterPresenterInterface {
    void validateCredentials(String username, String password);

    void onDestroy();
}
