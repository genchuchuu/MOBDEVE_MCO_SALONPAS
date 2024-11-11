package com.mobdeve.salonpas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StylistAdapter extends RecyclerView.Adapter<StylistAdapter.StylistViewHolder> {

    private List<String> stylistList;

    public StylistAdapter(List<String> stylistList) {
        this.stylistList = stylistList;
    }

    @NonNull
    @Override
    public StylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stylist_item, parent, false);
        return new StylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StylistViewHolder holder, int position) {
        String stylistName = stylistList.get(position);
        holder.stylistNameTextView.setText(stylistName);
    }

    @Override
    public int getItemCount() {
        return stylistList.size();
    }

    static class StylistViewHolder extends RecyclerView.ViewHolder {
        TextView stylistNameTextView;

        public StylistViewHolder(@NonNull View itemView) {
            super(itemView);
            stylistNameTextView = itemView.findViewById(R.id.stylistName);
        }
    }
}
