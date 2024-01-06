package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; //связь с разметкой
    private RecyclerView.Adapter adapter; //между разметкой и данными
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(getRecyclerViewItem());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private ArrayList<RecyclerViewItem> getRecyclerViewItem(){
        ArrayList<RecyclerViewItem> list = new ArrayList<>();
        list.add(new RecyclerViewItem("01/01/2024 08:00", "140/90"));
        list.add(new RecyclerViewItem("01/01/2024 18:00", "135/85"));
        list.add(new RecyclerViewItem("01/02/2024 08:00", "130/90"));
        list.add(new RecyclerViewItem("01/02/2024 19:00", "120/90"));
        list.add(new RecyclerViewItem("01/03/2024 08:20", "150/95"));
        list.add(new RecyclerViewItem("01/03/2024 17:55", "143/94"));
        list.add(new RecyclerViewItem("01/04/2024 17:50", "120/90"));
        list.add(new RecyclerViewItem("01/01/2024 08:00", "140/90"));
        list.add(new RecyclerViewItem("01/01/2024 18:00", "135/85"));
        list.add(new RecyclerViewItem("01/02/2024 08:00", "130/90"));
        list.add(new RecyclerViewItem("01/02/2024 19:00", "120/90"));
        list.add(new RecyclerViewItem("01/03/2024 08:20", "150/95"));
        list.add(new RecyclerViewItem("01/03/2024 17:55", "143/94"));
        list.add(new RecyclerViewItem("01/04/2024 17:50", "120/90"));
        return list;
    }

}