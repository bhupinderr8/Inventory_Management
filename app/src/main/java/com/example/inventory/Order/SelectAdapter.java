package com.example.inventory.Order;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.R;
import com.example.inventory.DataObject.itemObject;
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
    HashMap<String, Integer> mList;
    public SelectAdapter(@NonNull FirebaseRecyclerOptions<itemObject> options) {
        super(options);
        mList=new HashMap<>();
    }

    @Override
    protected void onBindViewHolder(@NonNull final SelectViewHolder holder, int position, @NonNull final itemObject model) {
        holder.productName.setText(model.getItemName());
        holder.imageView.setImageURI(Uri.parse(model.getImage()));
        holder.price.setText("");
        holder.qty.setText("0");

        holder.qtySeekBar.setMax(model.getPrice());

        holder.qtySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.qty.setText(String.valueOf(progress));
                String p = String.valueOf(progress*model.getPrice()) + " Rs";
                holder.price.setText(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_list_item, parent, false);

        return new SelectViewHolder(view);
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
    }
}
