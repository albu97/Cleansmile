package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
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

        Button logButton = findViewById(R.id.LogButton);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText username = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);
        Button newUser = findViewById(R.id.newUser);
        TextView textZoom = findViewById(R.id.textZoom);

        String user = username.getText().toString();
        String passWord = password.getText().toString();
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxScreenSize = Math.max(size.x, size.y);

        zoom.setMax(maxScreenSize);

        //DB anbindung erforderlich (ROOM)
        //Bei Clicken von button -> prüfen ob Daten korrekt sind
        //Bei newUser -> prüfen ob Daten noch nicht vorhanden sind
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlterWaehlenActivity.class);
                startActivity(intent);
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
             float textSize = progress*1.5f;
             textZoom.setTextSize(textSize);
             logButton.setTextSize(textSize);
             username.setTextSize(textSize);
             password.setTextSize(textSize);
             newUser.setTextSize(textSize);


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