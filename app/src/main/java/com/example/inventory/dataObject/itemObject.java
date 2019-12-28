package com.example.inventory.dataObject;

public class itemObject {
    private String itemName;
    private String itemNumber;
    private String image;
    private String description;
    private Integer qty;

    public itemObject(String itemName, String image, String description, Integer qty) {
        this.itemName = itemName;
        this.image = image;
        this.description = description;
        this.qty = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
