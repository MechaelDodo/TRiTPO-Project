package com.example.alex_.project;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.layout.simple_spinner_item;

public class AddCarActivity extends Activity {
    public String AddCarStr;
    public String AddTankVolumeStr;
    public String HowMuchOilStr;
    public String WhatMileageStr;
    public ArrayAdapter<String> adapter;
    public SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        final TextView AddCarText = (TextView) findViewById(R.id.AddCarText);
        final TextView AddTankVolumeText = (TextView) findViewById(R.id.AddTankVolumeText);
        final TextView HowMuchOilText = (TextView) findViewById(R.id.HowMuchOilText);
        final TextView WhatMileageText = (TextView) findViewById(R.id.WhatMileageText);
        final MainActivity mainActivity = new MainActivity();
        Button SaveCarBtn = (Button) findViewById(R.id.SaveCarBtn);
        SaveCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCarStr = AddCarText.getText().toString();
                AddTankVolumeStr = AddTankVolumeText.getText().toString();
                HowMuchOilStr =HowMuchOilText.getText().toString();
                WhatMileageStr = WhatMileageText.getText().toString();
                database = getBaseContext().openOrCreateDatabase("app.db", SQLiteDatabase.OPEN_READWRITE, null);
                if (!AddCarStr.isEmpty() & !AddTankVolumeStr.isEmpty() & !HowMuchOilStr.isEmpty() & !WhatMileageStr.isEmpty()){
                    int tankVol = Integer.parseInt(AddTankVolumeStr);
                    int howMuchOil = Integer.parseInt(HowMuchOilStr);
                    int whatMileage = Integer.parseInt(WhatMileageStr);
                    ContentValues insertValues = new ContentValues();
                       // database.execSQL("INSERT INTO car(name, tankvolume, mileage, intanknow) VALUES(" +
                           //     AddCarStr + "," + tankVol + "," + howMuchOil + "," + whatMileage + ")");

                    insertValues.put("name", AddCarStr);
                    insertValues.put("tankvolume", tankVol);
                    insertValues.put("mileage", whatMileage);
                    insertValues.put("intanknow", howMuchOil);
                    database.insert("car", null, insertValues);
                    mainActivity.myList.add(AddCarStr);
                    Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
