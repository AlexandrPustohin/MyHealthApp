package com.example.myhealthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private ArrayList<RecyclerViewItem> arrayList;

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = arrayList.get(position);//получаем элемент
        //и данные элемента
        holder.date.setText(recyclerViewItem.getTextDate());
        holder.press.setText(recyclerViewItem.getTextPress());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView press;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            date  = itemView.findViewById(R.id.textDate);
            press = itemView.findViewById(R.id.textPress);
        }
    }
}



















