package com.example.inventory.ItemDetail;

import com.example.inventory.DataObject.buyerObject;

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

    String getItemDescription();

    String getPrice();

    void setName(String itemName);

    void setPrice(String valueOf);

    void setImage(String image);

    void setNameEnable(boolean b);

    void setPriceEnable(boolean b);

    void setImageEnable(boolean b);

    void addBuyer(buyerObject obj);

    String getCurrentSellerId();

    void setSpinnerEnable(boolean b);
}
