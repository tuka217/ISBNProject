package weeia.isbnapp.interactor;

/**
 * Created by Anna on 2017-04-18.
 */

public interface RegisterInteractorInterface {

    interface OnLoginFinishedListener {
        void onError();
        void onSuccess();
    }

    void register(String username, String password, OnLoginFinishedListener listener);
}
