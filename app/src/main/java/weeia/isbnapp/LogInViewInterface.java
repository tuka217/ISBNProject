package weeia.isbnapp;

/**
 * Created by Anna on 2017-04-22.
 */
public interface LogInViewInterface {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void makeSuccessToast(String token);

    void makeWrongDataToast();
}
