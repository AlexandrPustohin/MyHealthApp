package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

import java.util.ArrayList;

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
        DataBaseHandler dbh = new DataBaseHandler(this);
        /*dbh.addType(new Type("Давление", "мм рт.ст"));
        dbh.addType(new Type("Глюкоза", "ммоль/л"));
        dbh.addType(new Type("Температура", "С"));*/
        Type type = dbh.getTypeByName("Давление");
        Log.d("Type:  ", type.getName()+ ", desc: "+type.getDescription());
    }


    private ArrayList<HealthInfoItem> getRecyclerViewItem(){
        ArrayList<HealthInfoItem> list = new ArrayList<>();
        list.add(new HealthInfoItem("01/01/2024 08:00", "Давление","140/90"));
        list.add(new HealthInfoItem("01/01/2024 18:00", "Давление","135/85"));
        list.add(new HealthInfoItem("01/02/2024 08:00", "Давление","130/90"));
        list.add(new HealthInfoItem("01/02/2024 19:00", "Давление","120/90"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Давление","150/95"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Температура","36,6"));
        list.add(new HealthInfoItem("01/03/2024 17:55", "Давление","143/94"));
        list.add(new HealthInfoItem("01/04/2024 17:50", "Давление","120/90"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Сахар","5,5"));
        list.add(new HealthInfoItem("01/01/2024 08:00", "Давление","140/90"));
        list.add(new HealthInfoItem("01/01/2024 18:00", "Давление","135/85"));
        list.add(new HealthInfoItem("01/02/2024 08:00", "Давление","130/90"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Давление","150/95"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Тепература","37.2"));
        list.add(new HealthInfoItem("01/02/2024 19:00", "Давление","120/90"));
        list.add(new HealthInfoItem("01/03/2024 08:20", "Давление","150/95"));
        list.add(new HealthInfoItem("01/03/2024 17:55", "Давление","143/94"));
        list.add(new HealthInfoItem("01/04/2024 17:50", "Давление","120/90"));
        return list;
    }

}