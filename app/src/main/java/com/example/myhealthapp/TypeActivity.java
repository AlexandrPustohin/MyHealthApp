package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myhealthapp.data.DataBaseHandler;

import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {
    private RecyclerView recyclerView; //связь с разметкой
    private RecyclerView.Adapter adapter; //между разметкой и данными
    private RecyclerView.LayoutManager layoutManager;
    private DataBaseHandler dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        dbh = new DataBaseHandler(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setRecycler(){
        recyclerView = findViewById(R.id.recycleViewType);
        recyclerView.setHasFixedSize(true);

        adapter = getAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    private RecyclerViewAdapterType getAdapter(){
        return new RecyclerViewAdapterType(new ArrayList<>(dbh.getAllTypes()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_type, menu);
        return true;
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        //смена курсора для обновления после сохранения в БД
        setRecycler();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_type) {//список типов измерений
            Intent intent = new Intent(TypeActivity.this, AddTypeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}