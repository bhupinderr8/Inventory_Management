package com.example.inventory.dataObject;

public class contentObject {
    private String billId;
    private String itemId;
    private Integer qty;

    public contentObject(String billId, String itemId, Integer qty) {
        this.billId = billId;
        this.itemId = itemId;
        this.qty = qty;
    }

    public String getBillId() {
        return billId;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQty() {
        return qty;
    }
}
