package com.hocheol.dagger2demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hocheol.dagger2demo.model.RecyclerData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<RecyclerData> listData;

    public void setListData(List<RecyclerData> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        RecyclerData recyclerData = listData.get(0);

        holder.tvTitle.setText(recyclerData.getName());
        holder.tvDesc.setText(recyclerData.getDescription());
        Glide.with(holder.thumbImage)
                .load(recyclerData.getOwner().getAvatar_url())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.thumbImage);
    }

    @Override
    public int getItemCount() {
        if (listData != null)
            return listData.size();
        else
            return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbImage;
        TextView tvTitle;
        TextView tvDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbImage = itemView.findViewById(R.id.thumbImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

}
