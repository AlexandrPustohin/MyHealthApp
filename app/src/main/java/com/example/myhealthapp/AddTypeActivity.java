package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AddTypeActivity extends AppCompatActivity {
    private TextView name;
    private TextView category;
    private TextView description;
    private Button btnAddType;
    private DataBaseHandler dbh;
    private String type;
    private String desc;
    private String categ;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);

        dbh = new DataBaseHandler(this);
        name = findViewById(R.id.type_name);
        category = findViewById(R.id.type_categorye);
        description = findViewById(R.id.measurement_type);
        btnAddType = findViewById(R.id.btn_save_type);



        Intent intent = getIntent();

        if(intent!=null && intent.getStringExtra("type")!=null){
            type = intent.getStringExtra("type");
            desc = intent.getStringExtra("desc");
            id = intent.getIntExtra("id",0);
        }
       if(intent!=null && intent.getStringExtra("category")!=null){
            categ =  intent.getStringExtra("category");
       }

        name.setText(type);
        description.setText(desc);
        category.setText(categ);
        btnAddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(type==null && typeExist(name.getText().toString())){
                    showToast("Уже есть такое измерение!");
                } else if (type==null){
                    dbh.addType(getInfo());
                    back();
                } else {

                    Type upType = getInfo();
                    upType.setId(id);
                    int updateRows = dbh.updateType(upType);
                    showToast("Измения сохранены.");

                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.type_add);
        setSupportActionBar(toolbar);
    }

    private void back(){
        Intent intent = new Intent(AddTypeActivity.this, TypeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    private void showToast(String text){
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
        toast.show();
    }
    private boolean typeExist(String nameType) {
        return dbh.getAllTypes().stream().anyMatch(t->t.getName().equalsIgnoreCase(nameType));
    }

    private Type getInfo(){
        Type type = new Type();
        type.setName(name.getText().toString());
        type.setCategory(category.getText().toString());
        type.setDescription(description.getText().toString());
        return type;
    }
}