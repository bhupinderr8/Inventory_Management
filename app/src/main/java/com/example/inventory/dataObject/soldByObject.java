package com.example.inventory.dataObject;

import java.util.Date;

public class soldByObject {
    private String sellerId;
    private String itemId;
    private Integer qty;
    private Date date;

    public soldByObject(String sellerId, String itemId, Integer qty) {
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.qty = qty;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQty() {
        return qty;
    }

    public Date getDate() {
        return date;
    }
}
