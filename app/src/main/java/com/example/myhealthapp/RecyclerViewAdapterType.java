package com.example.myhealthapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

import java.util.ArrayList;

public class RecyclerViewAdapterType extends RecyclerView.Adapter<RecyclerViewAdapterType.RecyclerViewViewHolder> {
    private ArrayList<Type> arrayList;
    Context context;
    public RecyclerViewAdapterType(ArrayList<Type> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_type, parent, false);
        RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder,  int position) {
        Type typeItem = arrayList.get(position);//получаем элемент
        //и данные элемента
        holder.textTypeCard.setText(typeItem.getName());
        holder.measurementCard.setText(typeItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



     class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textTypeCard;
        public TextView measurementCard;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textTypeCard =  itemView.findViewById(R.id.text_type_card);
            measurementCard =  itemView.findViewById(R.id.measurement_card);

        }

        @Override
        public void onClick(View view) {
            Type type = arrayList.get(getAdapterPosition());
            Intent intent = new Intent(context, AddTypeActivity.class);
            //Log.d("type.getId(): ", ""+type);
            intent.putExtra("id", type.getId());
            intent.putExtra("type", type.getName());
            intent.putExtra("desc", type.getDescription());
            context.startActivity(intent);
        }
    }
}



















