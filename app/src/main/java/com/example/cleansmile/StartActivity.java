package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.Button);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText user = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);
        Button newUser = findViewById(R.id.newUser);

        String a = user.getText().toString();
        String b = password.getText().toString();
        //DB anbindung erforderlich (ROOM)
        //Bei Clicken von button -> prüfen ob Daten korrekt sind
        //Bei newUser -> prüfen ob Daten noch nicht vorhanden sind
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
    //TESt

}