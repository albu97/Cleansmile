package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.Button);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText user = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);

    }

    //TESt

}