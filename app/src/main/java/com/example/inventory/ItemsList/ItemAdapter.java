package com.example.inventory.ItemsList;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    ArrayList<itemObject> data;
    private OnClickListener listener;

    public ItemAdapter(ArrayList<itemObject> data, OnClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        itemObject model = data.get(position);
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));
        String priceString = model.getPrice() + " Rs";
        holder.price.setText(priceString);
        String qtyString = Integer.toString(model.getQty());
        holder.qty.setText(qtyString);

        holder.setClickListener(listener, model);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productName, price, qty;
        LinearLayout parentLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.activity_image_view);
            productName = itemView.findViewById(R.id.activity_product_name);
            price = itemView.findViewById(R.id.activity_price);
            qty = itemView.findViewById(R.id.activity_qty);
            parentLayout = itemView.findViewById(R.id.list_item_parent);
        }

        public void setClickListener(final OnClickListener listener, final itemObject model) {
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(model);
                    return false;
                }
            });
        }
    }

}
