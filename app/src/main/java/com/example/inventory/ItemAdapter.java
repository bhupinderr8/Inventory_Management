package com.example.inventory;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public ItemAdapter(@NonNull FirebaseRecyclerOptions<itemObject> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull itemObject model) {
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));;
        holder.price.setText(model.getPrice());
        holder.qty.setText(model.getQty());

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

            imageView = itemView.findViewById(R.id.image_view);
            productName = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.quantity);
        }
    }
}
