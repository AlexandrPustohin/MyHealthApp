package com.example.myhealthapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private ArrayList<HealthInfoItem> arrayList;
    private Context context;
    private DataBaseHandler dbh;
    public RecyclerViewAdapter(ArrayList<HealthInfoItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        dbh = new DataBaseHandler(context);
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
        holder.descr.setText(healthInfoItem.getTextDescr());
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_info_item);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if(id ==R.id.del_info) {
                            dbh.deleteInfo(healthInfoItem);
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

    private void back(){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView date;
        public TextView info;
        public TextView type;
        public TextView descr;
        public TextView buttonViewOption;
        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            date  = itemView.findViewById(R.id.textDate);
            info = itemView.findViewById(R.id.textInfo);
            type =  itemView.findViewById(R.id.textTypeCard);
            descr =  itemView.findViewById(R.id.measurementCard);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
        }

        @Override
        public void onClick(View view) {
            HealthInfoItem healthInfoItem = arrayList.get(getAdapterPosition());
            Intent intent = new Intent(context, AddHealthInfo.class);
            //Log.d("type.getId(): ", ""+type);
            intent.putExtra("id", String.valueOf(healthInfoItem.getId()));
            intent.putExtra("date", healthInfoItem.getTextDate());
            intent.putExtra("info", healthInfoItem.getTextInfo());
            intent.putExtra("type", healthInfoItem.getTextType());
            context.startActivity(intent);
        }
    }
}



















