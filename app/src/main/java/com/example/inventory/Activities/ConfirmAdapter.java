package com.example.inventory.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inventory.R;
import com.example.inventory.dataObject.itemObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ConfirmAdapter extends ArrayAdapter<itemObject> {

    Context mContext;
    int mResource;

    public ConfirmAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<itemObject> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getItemName();
        Integer price = getItem(position).getPrice();
        Integer qty = getItem(position).getQty();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView itemName = (TextView) convertView.findViewById(R.id.confirm_activity_product_name);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.confirm_activity_price);
        TextView itemQty = (TextView) convertView.findViewById(R.id.confirm_activity_qty);

        itemName.setText(name);
        itemPrice.setText(price);
        itemQty.setText(qty);

        return convertView;
    }
}
