package com.example.inventory.Login;

import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.SessionImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView view;
    private SessionImpl session;
    private LoginRepository repository;

    public LoginPresenterImpl(LoginView loginActivity, SessionImpl session) {
        view = loginActivity;
        this.session = session;
        repository = new FireBaseHelper();
    }

    @Override
    public void initSignIn(String username, String password) {
        if (isValidCredentials(username, password)) {
            view.showProgress();
            repository.initSignIn(username, password);
        }
    }

    private boolean isValidCredentials(String username, String password) {

        if (username.length() < 5) {
            view.show("username too Short");
            return false;
        } else if (password.length() < 5) {
            view.show("password too Short");
            return false;

        }
        return true;
    }

    @Override
    public void initSignIn() {
        if (session.isLogin()) {
            view.onSignInSucess();
        }
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        if (event.getEventType() == LoginEvent.onSignInSuccess) {
            view.hideProgress();
            view.clearText();
            session.doLogin(event.getUserName());
            view.onSignInSucess();
        } else {
            view.onSignInError(event.getError());
        }
    }


}
