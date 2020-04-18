package com.innovvscript.sockodemo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.innovvscript.sockodemo.model.Lab;
import java.util.List;

public class LabsAdapter extends RecyclerView.Adapter<LabsAdapter.MyViewHolder> {
    private Context context;
    private List<Lab> labs;
    private static final String TAG = "LabsAdapter";

    public LabsAdapter(Context context, List<Lab> labs) {
        this.context = context;
        this.labs = labs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.lab_item, parent,false);
        return new MyViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Lab lab = labs.get(position);
        holder.price.setText(lab.getPrice());
        holder.price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.payable.setText(lab.getPayable());
        holder.location.setText(lab.getAddress());
        float rate = Float.parseFloat(lab.getRating());
        float rating = rate/10;
        holder.rating.setText(String.valueOf(rating));
    }

    @Override
    public int getItemCount() {
        return labs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView location, price, payable, rating;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
            rating = itemView.findViewById(R.id.rating);
            payable = itemView.findViewById(R.id.new_price);
            price = itemView.findViewById(R.id.old_price);
            location = itemView.findViewById(R.id.loc);
        }
    }
}