package com.example.myhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myhealthapp.data.DataBaseHandler;
import com.example.myhealthapp.model.HealthInfoItem;
import com.example.myhealthapp.utils.DateTransform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddHealthInfo extends AppCompatActivity {

    Calendar dateAndTime= Calendar.getInstance();

    private TextView date;
    private TextView time;
    private Spinner spinner;
    private Button btnSave;
    private EditText info;
    private DataBaseHandler dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_info);
        dbh = new DataBaseHandler(this);
        date = findViewById(R.id.data);
        time = findViewById(R.id.time);
        Date currentDT = new Date();
        date.setText(DateTransform.DATE_FORMAT.format(currentDT));
        time.setText(DateTransform.TIME_FORMAT.format(currentDT.getTime()));

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =
                               new ArrayAdapter(this,R.layout.spinner, getArrayType()   );
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        info = findViewById(R.id.info);
        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    dbh.addInfo(getInfo());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(AddHealthInfo.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
    private HealthInfoItem getInfo(){
        HealthInfoItem hi = new HealthInfoItem();
        hi.setTextDate(DateTransform.UNIX_DATE_TIME.format(dateAndTime.getTime()));
        hi.setTextType(spinner.getSelectedItem().toString());
        hi.setTextInfo(info.getText().toString());
        return hi;
    }
    private ArrayList<String> getArrayType(){
        return dbh.getAllTypes().stream().map(t->t.getName()).collect(Collectors.toCollection(ArrayList::new));
    }
    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(AddHealthInfo.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(AddHealthInfo.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    // установка начальных даты и времени
    private void setInitialDate() {
        date.setText(DateTransform.DATE_FORMAT.format(dateAndTime.getTimeInMillis()));
        /*date.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR
                        ));*/

    }
    private void setInitialTime() {
        time.setText(DateTransform.TIME_FORMAT.format(dateAndTime.getTimeInMillis()));
        /*time.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME |DateUtils.FORMAT_ABBREV_TIME));*/
    }
    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };


}