package com.example.inventory.Register;

import com.example.inventory.DataObject.userObject;
import com.example.inventory.utils.FireBaseHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class RegisterPresenterImpl implements RegisterPresenter {

    RegisterView view;
    RegisterRepository repository;
    public RegisterPresenterImpl(RegisterViewImpl view) {
        this.view = view;
        repository = new FireBaseHelper();
    }

    @Override
    public void addUser(userObject userObject) {
        view.disableInputs();
        view.showProgess();
        repository.addUser(userObject);
    }

    @Override
    public void onStat() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(RegisterEvent event)
    {
        if(event.getEventType() == RegisterEvent.onSignUpSuccess)
        {
            view.hideProgess();
            view.onSignupSuccess();
        }

    }
}
