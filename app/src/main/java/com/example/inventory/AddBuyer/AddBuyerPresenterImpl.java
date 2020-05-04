package com.example.inventory.AddBuyer;

import com.example.inventory.DataObject.buyerObject;
import com.example.inventory.utils.FireBaseHelper;

public class AddBuyerPresenterImpl implements AddBuyerPresenter {
    AddBuyerView view;
    AddBuyerRepository repository;


    public AddBuyerPresenterImpl(AddBuyerView view) {
        this.view = view;
        this.repository = new FireBaseHelper();
    }

    @Override
    public void OnAddBuyerButton() {
        if(view.checkValuesSet()){

            buyerObject obj = new buyerObject();
            obj.setBuyerEmail(view.getBuyerEmail());
            obj.setPhoneNumber(view.getBuyerPhone());
            obj.setBuyerDescription(view.getBuyerDescription());
            obj.setName(view.getBuyerName());

            repository.insertBuyer(obj);
            view.show(obj.getName() + "  Added");
            view.finishActivity();
        }
    }
}
