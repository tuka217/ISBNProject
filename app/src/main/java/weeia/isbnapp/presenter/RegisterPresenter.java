package weeia.isbnapp.presenter;

import weeia.isbnapp.RegisterViewInterface;
import weeia.isbnapp.interactor.RegisterInteractor;
import weeia.isbnapp.interactor.RegisterInteractorInterface;

/**
 * Created by Anna on 2017-04-18.
 */

public class RegisterPresenter implements RegisterPresenterInterface, RegisterInteractorInterface.OnLoginFinishedListener {

    private RegisterViewInterface registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenter(RegisterViewInterface loginView) {
        this.registerView = loginView;
        this.registerInteractor = new RegisterInteractor();
    }

    @Override public void validateCredentials(String username, String password) {
        if (registerView != null) {
            registerView.showProgress();
        }

        registerInteractor.register(username, password, this);
    }

    @Override public void onDestroy() {
        registerView = null;
    }

    @Override public void onError() {
        if (registerView != null) {
            registerView.setWrongDataError();
            registerView.hideProgress();
        }
    }

    @Override public void onSuccess() {
        if (registerView != null) {
            registerView.makeSuccessToast();
        }
    }
}
