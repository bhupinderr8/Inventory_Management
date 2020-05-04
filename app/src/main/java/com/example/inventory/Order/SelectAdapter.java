package com.example.inventory.Order;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.SelectViewHolder> {
    ArrayList<itemObject> data;
    HashMap<String, Integer> mList;
    SelectAdapterView view;

    public SelectAdapter(ArrayList<itemObject> data, SelectViewImpl context) {
        this.data = data;
        mList = new HashMap<>();
        view = context;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_list_item, parent, false);

        return new SelectAdapter.SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        itemObject model = data.get(position);
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));
        holder.price.setText("");
        holder.qty.setText("0");

        holder.qtySeekBar.setMax(model.getPrice());

        holder.setQtySeekBarChangeListener(model);
        holder.setQtyTextChangeListener(model);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SelectViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView price,productName;
        TextView qty;
        SeekBar qtySeekBar;
        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.select_image_view);
            productName = itemView.findViewById(R.id.select_name);
            price = itemView.findViewById(R.id.select_price);
            qty = itemView.findViewById(R.id.select_qty_edit_text);
            qtySeekBar = itemView.findViewById(R.id.select_qty_seek_bar);
        }

        public void setQtySeekBarChangeListener(final itemObject model) {
            qtySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    qty.setText(String.valueOf(progress));
                    String p = String.valueOf(progress*model.getPrice()) + " Rs";
                    price.setText(p);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        public void setQtyTextChangeListener(final itemObject model)
        {
            qty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    view.informDataChanged();
                    String val = qty.getText().toString();
                    if(val.isEmpty())
                        val="0";
                    Integer finalValue=Integer.parseInt(val);
                    finalValue=Math.max(finalValue, 0);
                    finalValue=Math.min(finalValue, model.getQty());

//                holder.qty.setText(String.valueOf(finalValue));
                    if(finalValue>0)
                        mList.put(model.getItemNumber(), finalValue);
                    else
                        mList.remove(model.getItemNumber());
                }
            });
        }
    }
}
