package com.example.inventory.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inventory.R;
import com.example.inventory.DataObject.itemObject;

import java.util.ArrayList;

public class ConfirmAdapter extends ArrayAdapter<itemObject> {

    Context mContext;
    int mResource;

    public ConfirmAdapter(@NonNull Context context, int resource, @NonNull ArrayList<itemObject> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        itemObject item = getItem(position);
        if(item == null)
        {
            item = new itemObject();
            item.setItemName("Empty");
            item.setQty(0);
            item.setPrice(0);
        }


        String name = item.getItemName();
        Integer price = item.getPrice();
        Integer qty = item.getQty();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView itemName = convertView.findViewById(R.id.confirm_activity_product_name);
        TextView itemPrice = convertView.findViewById(R.id.confirm_activity_price);
        TextView itemQty = convertView.findViewById(R.id.confirm_activity_qty);

        itemName.setText(name);
        itemPrice.setText(String.valueOf(price));
        itemQty.setText(String.valueOf(qty));

        return convertView;
    }
}