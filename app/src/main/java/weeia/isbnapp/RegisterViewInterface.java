package weeia.isbnapp;

/**
 * Created by Anna on 2017-04-18.
 */
public interface RegisterViewInterface {
    void showProgress();

    void hideProgress();

    void setWrongDataError();

    void makeSuccessToast();
}
