package com.example.myhealthapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

import java.util.ArrayList;

public class RecyclerViewAdapterType extends RecyclerView.Adapter<RecyclerViewAdapterType.RecyclerViewViewHolder> {
    private ArrayList<Type> arrayList;
    private Context context;

    private DataBaseHandler dbh;
    public RecyclerViewAdapterType(ArrayList<Type> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        dbh = new DataBaseHandler(context);
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
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_type_item);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id ==R.id.del_type) {
                            dbh.deleteType(typeItem);
                            back();
                        }
                        return false;
                    }

                });
                //displaying the popup
                popup.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void back(){
        Intent intent = new Intent(context, TypeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

     class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textTypeCard;
        public TextView measurementCard;
        public TextView buttonViewOption;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textTypeCard =  itemView.findViewById(R.id.text_type_card);
            measurementCard =  itemView.findViewById(R.id.measurement_card);
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);

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



















