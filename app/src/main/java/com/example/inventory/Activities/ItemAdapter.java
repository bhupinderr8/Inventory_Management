package com.example.inventory.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.dataObject.itemObject;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemAdapter extends FirebaseRecyclerAdapter<itemObject, ItemAdapter.ItemViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context mContext;

    public ItemAdapter(@NonNull FirebaseRecyclerOptions<itemObject> options, Context mContext) {
        super(options);
        mContext=mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull itemObject model) {
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));
        String priceString = "Price " + String.valueOf(model.getPrice());
        holder.price.setText(priceString);
        String qtyString = "Qty " + String.valueOf(model.getQty());
        holder.qty.setText(qtyString);

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ItemViewHolder(view);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView productName, price, qty;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.activity_image_view);
            productName = itemView.findViewById(R.id.activity_product_name);
            price = itemView.findViewById(R.id.activity_price);
            qty = itemView.findViewById(R.id.activity_qty);
        }
    }
}
