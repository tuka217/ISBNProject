package weeia.isbnapp.presenter;

import weeia.isbnapp.LogInViewInterface;
import weeia.isbnapp.interactor.LogInInteractor;
import weeia.isbnapp.interactor.LogInInteractorInterface;

/**
 * Created by Anna on 2017-04-22.
 */
public class LogInPresenter implements LogInPresenterInterface, LogInInteractorInterface.OnLoginFinishedListener {

    private LogInViewInterface loginView;
    private LogInInteractorInterface loginInteractor;

    public LogInPresenter(LogInViewInterface loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LogInInteractor();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess(String token) {
        if (loginView != null) {
            loginView.makeSuccessToast(token);
            loginView.hideProgress();

        }
    }

    @Override
    public void onFailure() {
        if (loginView != null) {
            loginView.makeWrongDataToast();
            loginView.hideProgress();
        }
    }
}
