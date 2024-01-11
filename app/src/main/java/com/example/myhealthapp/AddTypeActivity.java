package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.model.Type;

public class AddTypeActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private Button btnAddType;
    private DataBaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);

        dbh = new DataBaseHandler(this);
        name = findViewById(R.id.type_name);
        description = findViewById(R.id.measurement_type);

        btnAddType = findViewById(R.id.btn_save_type);
        btnAddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbh.addType(getInfo());
                Intent intent = new Intent(AddTypeActivity.this, TypeActivity.class);
                startActivity(intent);
            }
        });
    }
    private Type getInfo(){
        Type type = new Type();
        type.setName(name.getText().toString());
        type.setDescription(description.getText().toString());
        return type;
    }
}