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
import android.view.Menu;
import android.view.MenuItem;
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

    private FloatingActionButton floatingActionButton;
    private DataBaseHandler dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh = new DataBaseHandler(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddHealthInfo.class);
                startActivity(intent);

            }
        });
        setRecycler();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_type) {//список типов измерений
            Intent intent = new Intent(MainActivity.this, TypeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.reprts) {//отчеты
            return true;
        } else if (id == R.id.action_settings) {//настройки
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setRecycler(){
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        //adapter = new RecyclerViewAdapter(new ArrayList<>(dbh.getAllInfo()));
        adapter = getAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    private RecyclerViewAdapter getAdapter(){
        return new RecyclerViewAdapter(new ArrayList<>(dbh.getAllInfoWithMeasurement()));
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        //обновление после сохранения в БД
        setRecycler();
    }
}