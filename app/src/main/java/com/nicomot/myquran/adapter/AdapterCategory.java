package com.nicomot.myquran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicomot.myquran.R;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    List<String> listButton;

    public AdapterCategory(List<String> listButton) {
        this.listButton = listButton;
    }

    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout_button,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {
        holder.textView.setText(listButton.get(position));
    }

    @Override
    public int getItemCount() {
        return listButton.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.id_category_name);
        }
    }
}
