package weeia.isbnapp.interactor;

/**
 * Created by Anna on 2017-04-22.
 */
public interface LogInInteractorInterface {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess(String token);

        void onFailure();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
