package com.example.inventory.Activities;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.dataObject.itemObject;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;

public class SelectAdapter extends  FirebaseRecyclerAdapter<itemObject, SelectAdapter.SelectViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    HashMap<String, itemObject> mList;
    public SelectAdapter(@NonNull FirebaseRecyclerOptions<itemObject> options, HashMap<String, itemObject> list) {
        super(options);
        mList=list;
    }

    @Override
    protected void onBindViewHolder(@NonNull final SelectViewHolder holder, int position, @NonNull final itemObject model) {
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));;
        holder.price.setText(model.getPrice());
        holder.qty.setText("0");

        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = holder.qty.getText().toString();
                int finalValue=Integer.parseInt(val);

                if(finalValue < model.getQty())
                {
                    finalValue = finalValue+1;
                }
                holder.qty.setText(finalValue);
                itemObject obj = new itemObject();
                obj= model;
                obj.setQty(finalValue);
                mList.put(model.getItemNumber(), obj);
            }
        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = holder.qty.getText().toString();
                int finalValue=Integer.parseInt(val);
                if(finalValue==1)
                {
                    mList.remove(model.getItemNumber());
                }
                if(finalValue != 0)
                {
                    finalValue = finalValue-1;
                }
                itemObject obj = new itemObject();
                obj= model;
                obj.setQty(finalValue);
                mList.put(model.getItemNumber(), obj);
            }
        });

        holder.qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String val = holder.qty.getText().toString();
                int finalValue=Integer.parseInt(val);
                finalValue=Math.max(finalValue, 0);
                finalValue=Math.min(finalValue, model.getQty());

                itemObject obj = new itemObject();
                obj= model;
                obj.setQty(finalValue);
                mList.put(model.getItemNumber(), obj);

            }
        });
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_list_item, parent, false);

        return new SelectAdapter.SelectViewHolder(view);
    }

    class SelectViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView price,productName;
        EditText qty;
        Button incrementButton, decrementButton;
        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            productName = itemView.findViewById(R.id.confirm_activity_product_name);
            price = itemView.findViewById(R.id.confirm_activity_price);
            qty = itemView.findViewById(R.id.confirm_activity_qty);
            incrementButton = itemView.findViewById(R.id.select_increment_button);
            decrementButton= itemView.findViewById(R.id.select_decrement_button);
        }
    }
}
