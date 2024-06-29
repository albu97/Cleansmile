package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.FileUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SensorDataActivity extends AppCompatActivity {
    private FileUtils.ProgressListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        ListView sensorDataListView = findViewById(R.id.sensor_data_list);
        ArrayList<String> sensorDataList = getIntent().getStringArrayListExtra("sensorData");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorDataList);
        sensorDataListView.setAdapter(adapter);

    }
    public void sensorActivclass(FileUtils.ProgressListener listener) {
        this.listener = listener;}
}