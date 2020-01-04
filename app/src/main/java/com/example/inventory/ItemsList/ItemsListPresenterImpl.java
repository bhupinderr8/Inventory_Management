package com.example.inventory.ItemsList;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.Session;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ItemsListPresenterImpl implements ItemsListPresenter {
    private ItemsListView view;
    private Session session;
    private ItemsListRepository repository;


    public ItemsListPresenterImpl(ItemsListView view, Session session) {
        this.view = view;
        this.session = session;
        repository = new FireBaseHelper();

        view.setTitleToUserName(session.getUserName());
        setupAdapter();

        pupulateItems();
    }

    private void pupulateItems() {
        repository.retrieveItems();
    }

    private void setupAdapter() {
        view.setAdapter();
    }

    @Override
    public void addDummyData() {
        itemObject gummibears = new itemObject(
                "Bottle",
                "android.resource://com.example.inventory/drawable/bottle",
                "Bottle Good.",
                97,
                97
        );
        addItem(gummibears);
    }

    private void addItem(itemObject item) {
        view.addItemToAdapter(item);
        view.OnAdapterChange();
        view.scrollToEnd();
    }

    @Override
    public void OnLogoutButton() {
        session.doLogout();
        view.launchLoginActivity();
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(ItemsListEvent event)
    {

        if(event.getEventType() == ItemsListEvent.onChildAdded)
        {
            view.addItemToAdapter(event.getItem());
            view.OnAdapterChange();
        }
        else if(event.getEventType() == ItemsListEvent.onChildRemoved)
        {
            view.removeValueFromAdapter(event.getItem().getItemNumber());
            view.OnAdapterChange();
        }
        else if(event.getEventType() == ItemsListEvent.onChildUpdated)
        {
            view.removeValueFromAdapter(event.getItem().getItemNumber());
            view.addItemToAdapter(event.getItem());
            view.OnAdapterChange();
        }
        else
        {
            view.show("Something Went Wrong");
        }
    }
}
