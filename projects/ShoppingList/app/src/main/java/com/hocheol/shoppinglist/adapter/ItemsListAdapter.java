package com.hocheol.shoppinglist.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hocheol.shoppinglist.R;
import com.hocheol.shoppinglist.db.Items;

import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.MyViewHolder> {

    private Context context;
    private List<Items> itemsList;
    private HandleItemsClick clickListener;

    public ItemsListAdapter(Context context, HandleItemsClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setItemList(List<Items> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsListAdapter.MyViewHolder holder, int position) {
        Items item = itemsList.get(position);

        holder.tvItemName.setText(item.itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(item);
            }
        });

        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(item);
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(item);
            }
        });

        if (item.completed) {
            holder.tvItemName.setPaintFlags(
                    holder.tvItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );
        } else {
            holder.tvItemName.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        if (itemsList == null || itemsList.size() == 0)
            return 0;
        else
            return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName;
        ImageView removeItem;
        ImageView editItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemName = itemView.findViewById(R.id.tvCategoryName);
            removeItem = itemView.findViewById(R.id.removeCategory);
            editItem = itemView.findViewById(R.id.editCategory);
        }

    }

    public interface HandleItemsClick {
        void itemClick(Items item);

        void removeItem(Items item);

        void editItem(Items item);
    }

}
