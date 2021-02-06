package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnClickListener {
        // tells adapter what item was pressed
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        // tells adapter what item was long pressed
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnClickListener clickListener;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = (OnLongClickListener) longClickListener;
    } // end of public itemsAdapter

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a view and return it
        return new ViewHolder(todoView);
    } // end of override nonnull public ViewHolder onCreateViewHolder
    
    // Binds data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grabs item at a position
        String item  = items.get(position);
        // bind item into the specified view holder
        holder.bind(item);
    } // end of override public void onBindViewHolder

    // tells recycler view how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    } // end of override public int getItemCount

    // container to provide east access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        } // end of public ViewHolder

        // update view inside the view holder w/ the data
        public void bind(String items) {
            tvItem.setText(items);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // notifies listener as to what item was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        } // end of public void bind
    } // end of class ViewHolder
} // end of public class itemsAdapter