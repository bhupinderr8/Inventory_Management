package com.example.inventory.NewItem;

import android.net.Uri;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.utils.FireBaseHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DetailsPresenterImpl implements DetailsPresenter {

    DetailsView view;
    DetailsRepository repository;

    public DetailsPresenterImpl(DetailsView view) {
        this.view = view;
        this.repository = new FireBaseHelper();
        if (view.getCurrentItemId().equals("")) {
            view.setTitle("Add NEw Item");

        } else {
            view.setTitle("Edit Item");
            repository.updateValues(view.getCurrentItemId());
        }
    }

    @Override
    public void onSubtractOneToQuantity() {
        String previousValueString = view.getQuantity();
        int previousValue;
        if (previousValueString.isEmpty()) {
            return;
        } else if (previousValueString.equals("0")) {
            return;
        } else {
            previousValue = Integer.parseInt(previousValueString);
            view.setQuantity(String.valueOf(previousValue - 1));
        }
        view.setInfoHasChanged(true);
    }

    @Override
    public void onSumOneToQuantity() {
        String previousValueString = view.getQuantity();
        int previousValue;
        if (previousValueString.isEmpty()) {
            previousValue = 0;
        } else {
            previousValue = Integer.parseInt(previousValueString);
        }
        view.setQuantity(String.valueOf(previousValue + 1));
        view.setInfoHasChanged(true);
    }

    @Override
    public void onImageSelectButton() {
        view.tryToOpenImageSelector();
        view.openImageSelector();
    }

    @Override
    public void onActionSave() {
        if (view.addItemToDb()) {
            if (view.getCurrentItemId() == "") {
                itemObject itemObj = new itemObject(
                        view.getCurrentName(),
                        view.getImage(),
                        view.getItemDescription(),
                        Integer.parseInt(view.getQuantity()),
                        Integer.parseInt(view.getPrice())
                );
                repository.insertItem(itemObj);
            } else {
                repository.updateItem(view.getCurrentItemId(), Integer.valueOf(view.getQuantity()));
            }
        }
        view.finishActtivity();
    }

    @Override
    public void deleteAllRowsFromTable() {
        repository.deleteAllItems();
    }

    @Override
    public void deleteOneItemFromTable(String itemId) {
        repository.deleteItem(itemId);
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
    public void onEventMainThread(itemObject item)
    {
        view.setName(item.getItemName());
        view.setPrice(String.valueOf(item.getPrice()));
        view.setQuantity(String.valueOf(item.getQty()));
        view.setImage(item.getImage());
        view.setNameEnable(false);
        view.setPriceEnable(false);
        view.setImageEnable(false);
    }

}
