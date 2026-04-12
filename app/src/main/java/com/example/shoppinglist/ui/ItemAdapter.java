package com.example.shoppinglist.ui;
import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.shoppinglist.R;
import com.example.shoppinglist.data.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ItemAdapter(List<Item> list) {
        this.itemList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            price = view.findViewById(R.id.tvPrice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.name.setText(item.name);
        holder.price.setText(String.valueOf(item.price));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Метод для Swipe
    public void removeItem(int position) {
        if (position < itemList.size()) {
            itemList.remove(position);
            notifyItemRemoved(position);
        }
    }
}