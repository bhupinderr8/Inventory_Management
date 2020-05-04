package com.example.inventory.AddSupplier;

import com.example.inventory.DataObject.supplierObject;
import com.example.inventory.utils.FireBaseHelper;

public class AddSupplierPresenterImpl implements AddSupplierPresenter {
    AddSupplierView view;
    AddSupplierRepository repository;


    public AddSupplierPresenterImpl(AddSupplierView view) {
        this.view = view;
        this.repository = new FireBaseHelper();
    }

    @Override
    public void OnAddSupplierButton() {
        if (view.checkValuesSet()) {

            supplierObject obj = new supplierObject();
            obj.setSupplierEmail(view.getSupplierEmail());
            obj.setPhoneNumber(view.getSupplierPhone());
            obj.setSupplierDescription(view.getSupplierDescription());
            obj.setName(view.getSupplierName());

            repository.insertSupplier(obj);
            view.show(obj.getName() + "  Added");
            view.finishActivity();
        }
    }
}
