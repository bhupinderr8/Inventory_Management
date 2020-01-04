package com.example.inventory.DataObject;

public class itemObject{

    private String itemName;
    private String itemNumber;
    private String image;
    private Integer price;
    private String description;
    private Integer qty;

    public itemObject() {
    }

    public itemObject(String itemName, String image, String description, Integer qty, Integer price) {
        this.itemName = itemName;
        this.image = image;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
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

    public int getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
