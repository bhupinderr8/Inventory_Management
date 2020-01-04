package com.example.inventory.Home;

import com.example.inventory.ItemsList.ItemsListViewImpl;
import com.example.inventory.NewItem.DetailsActivity;
import com.example.inventory.Register.RegisterViewImpl;
import com.example.inventory.utils.Session;

public class HomePresenterImpl implements HomePresenter{
    private HomeView view;
    private Session session;

    public HomePresenterImpl(HomeView view, Session session) {
        this.view=view;
        this.session=session;

        view.showName(session.getUserName());
    }

    @Override
    public void doLogout() {
        session.doLogout();
        view.OnLogout();
    }

    @Override
    public void itemButtonPressed() {
        view.show("Add Item");
        view.launchActivity(DetailsActivity.class);
    }

    @Override
    public void viewButtonPressed() {
        view.launchActivity(ItemsListViewImpl.class);
    }

    @Override
    public void orderButtonPressed() {
        view.launchActivity(RegisterViewImpl.class);
    }
}
