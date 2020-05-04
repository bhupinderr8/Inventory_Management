package com.example.inventory.ItemDetail;

import com.example.inventory.DataObject.supplierObject;

public interface DetailsView {
    String getCurrentItemId();

    void setTitle(String str);

    boolean getInfoHasChanged();

    void setInfoHasChanged(boolean val);

    String getQuantity();

    void setQuantity(String value);

    void tryToOpenImageSelector();

    void openImageSelector();

    void finishActtivity();

    boolean addItemToDb();

    String getCurrentName();

    String getImage();

    void setImage(String image);

    String getItemDescription();

    String getPrice();

    void setPrice(String valueOf);

    void setName(String itemName);

    void setNameEnable(boolean b);

    void setPriceEnable(boolean b);

    void setImageEnable(boolean b);

    void addSupplier(supplierObject obj);

    String getCurrentSellerId();

    void setSpinnerEnable(boolean b);
}
