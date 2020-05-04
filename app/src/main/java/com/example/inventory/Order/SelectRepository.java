package com.example.inventory.Order;

import java.util.HashMap;

public interface SelectRepository {
    void queryItemName(String text);

    void retrieveSelectItems();

    void updateItems(HashMap<String, Integer> mList);
}
