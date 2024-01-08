package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; //связь с разметкой
    private RecyclerView.Adapter adapter; //между разметкой и данными
    private RecyclerView.LayoutManager layoutManager;

    private SQLiteDatabase db = null;
    private Cursor docCursor;
    private SimpleCursorAdapter docAdapter;
    private CursorAdapter cursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler dbh = new DataBaseHandler(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddHealthInfo.class);
                startActivity(intent);

            }
        });
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        //adapter = new RecyclerViewAdapter(new ArrayList<>(dbh.getAllInfo()));
        adapter = new RecyclerViewAdapter(new ArrayList<>(dbh.getAllInfoWithMeasurement()));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        /*dbh.addType(new Type("Давление", "мм рт.ст"));
        dbh.addType(new Type("Глюкоза", "ммоль/л"));
        dbh.addType(new Type("Температура", "С"));*/
        //Type type = dbh.getTypeByName("Давление");
        //Log.d("Type:  ", type.toString());
        //dbh.addInfo(new HealthInfoItem("01/01/2024 08:00", "Давление","140/90"));
        /*for (HealthInfoItem info: dbh.getAllInfo()) {
            Log.d("Info:  ", info.toString());
        }*/

    }


    private ArrayList<HealthInfoItem> getRecyclerViewItem(){
        ArrayList<HealthInfoItem> list = new ArrayList<>();
        /*list.add(new HealthInfoItem("01/01/2024 08:00", "Давление","140/90"));
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
        list.add(new HealthInfoItem("01/04/2024 17:50", "Давление","120/90"));*/
        return list;
    }

}