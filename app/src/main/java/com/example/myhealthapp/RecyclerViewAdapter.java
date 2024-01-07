package com.example.myhealthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhealthapp.model.HealthInfoItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private ArrayList<HealthInfoItem> arrayList;

    public RecyclerViewAdapter(ArrayList<HealthInfoItem> arrayList) {
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
        HealthInfoItem healthInfoItem = arrayList.get(position);//получаем элемент
        //и данные элемента
        holder.date.setText(healthInfoItem.getTextDate());
        holder.info.setText(healthInfoItem.getTextInfo());
        holder.type.setText(healthInfoItem.getTextType());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView info;
        public TextView type;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            date  = itemView.findViewById(R.id.textDate);
            info = itemView.findViewById(R.id.textInfo);
            type =  itemView.findViewById(R.id.textType);
        }
    }
}



















