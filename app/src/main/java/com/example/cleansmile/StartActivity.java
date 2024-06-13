package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.*;
import javax.sql.DataSource;

public class StartActivity extends AppCompatActivity {
    private View contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.Button);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText username = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);
        Button newUser = findViewById(R.id.newUser);
        TextView textZoom = findViewById(R.id.textZoom);

        String user = username.getText().toString();
        String passWord = password.getText().toString();


        //DB anbindung erforderlich (ROOM)
        //Bei Clicken von button -> prüfen ob Daten korrekt sind
        //Bei newUser -> prüfen ob Daten noch nicht vorhanden sind
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlterWaehlenActivity.class);
                //String logIn = intent.getStringExtra(RealMainActivity.NAME);
            }
        });
        //Neuen User anlegen
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Video: https://www.youtube.com/watch?v=dJvpDtFrk0A
        zoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float textSize = progress * 1.5f;
                textZoom.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }


}