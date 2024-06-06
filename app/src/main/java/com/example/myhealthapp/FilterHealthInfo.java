package com.example.myhealthapp;




import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.utils.DateTransform;
import com.example.myhealthapp.utils.SharedPreferencesUtil;
import com.example.myhealthapp.utils.Util;

import java.util.Calendar;

public class FilterHealthInfo extends AppCompatActivity {
    Calendar dateAndTime= Calendar.getInstance();
    DataBaseHandler dbh;
    SharedPreferencesUtil sharedPreferencesUtil;
    TextView dateStart;
    TextView timeStart;
    TextView dateEnd;
    TextView timeEnd;
    Button btnCancelDate;
    Spinner spinner;
    Button getBtnCancelType;
    Button btnFilterSave;
    Button btnFilterCancel;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_health_info);
        sharedPreferences = this.getSharedPreferences(Util.SP_FILTER, Context.MODE_PRIVATE);
        sharedPreferencesUtil = new SharedPreferencesUtil(sharedPreferences);
        dbh = new DataBaseHandler(this);
        init();
        dateStart.setText(DateTransform.DATE_FORMAT.format(sharedPreferencesUtil.getDateStart()));
        /*timeStart.setText(DateTransform.TIME_FORMAT.format(getTimeStart()));
        dateEnd.setText(DateTransform.DATE_FORMAT.format(getDateEnd()));
        timeStart.setText(DateTransform.TIME_FORMAT.format(getTimeStart()));*/
    }

    private void init(){
        dateStart = findViewById(R.id.date_start);
        timeStart = findViewById(R.id.time_start); ;
        dateEnd = findViewById(R.id.date_end);;
        timeEnd = findViewById(R.id.time_end);;
        btnCancelDate = findViewById(R.id.btn_cancel_date);
        spinner = findViewById(R.id.spinner_filter);
        getBtnCancelType = findViewById(R.id.btn_cancel_type);;
        btnFilterSave = findViewById(R.id.btn_filter_save);
        btnFilterCancel = findViewById(R.id.btn_filter_cancel);
    }
}
