package com.example.inventory.Order;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.Session;
import com.example.inventory.utils.SessionImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SelectPresenterImpl implements SelectPresenter {
    private Session session;
    private SelectView view;
    private SelectRepository repository;
    private boolean confirm;

    public SelectPresenterImpl(SelectViewImpl selectView, SessionImpl session) {
        this.view = selectView;
        this.session = session;
        repository = new FireBaseHelper();
        confirm = false;
        setupAdapter();
        pupulateItems();
    }

    @Override
    public void onQueryTextChange(String text) {
        if (text.equals("")) {
            view.clearAllItems();
            pupulateItems();
            return;
        }
        view.clearAllItems();
        view.OnAdapterChange();
        text = text.trim();
        repository.queryItemName(text);
    }

    private void pupulateItems() {
        repository.retrieveSelectItems();
    }

    private void setupAdapter() {
        view.setAdapter();
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void OnConfirmItemClicked() {
        if (confirm)
            DialogConfirmedAction();
        else
            view.showPopUpWindow();
    }

    public void DialogConfirmedAction() {
        repository.updateItems(view.getValues());
        view.finishActivity();
    }

    @Override
    public void OnPopUpDismissed() {
        confirm = true;
    }

    @Override
    public void informDataChanged() {
        confirm = false;
    }

    @Subscribe
    public void onEventMainThread(SelectEvent event) {
        if (event.getEventType() == SelectEvent.onChildAdded) {
            addItemToAdapter(event.getItem());

        } else if (event.getEventType() == SelectEvent.onChildRemoved) {
            view.removeValueFromAdapter(event.getItem().getItemNumber());
            view.OnAdapterChange();
        } else if (event.getEventType() == SelectEvent.onChildUpdated) {
            view.removeValueFromAdapter(event.getItem().getItemNumber());
            addItemToAdapter(event.getItem());
        } else {
            view.show("Something Went Wrong");
        }
    }

    private void addItemToAdapter(itemObject item) {
        if (!view.itemInAdapter(item.getItemNumber())) {
            view.addItemToAdapter(item);
            view.OnAdapterChange();
            view.scrollToEnd();
        }
    }
}
