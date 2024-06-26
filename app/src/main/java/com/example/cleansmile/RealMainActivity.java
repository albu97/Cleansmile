package com.example.cleansmile;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RealMainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private TextView timerTextView;
    private WebView videoView;
    private Button startButton;
    private ArrayList<String> sensorDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Intent intent = getIntent();
        String holeselektiertesAlter = intent.getStringExtra(AlterWaehlenActivity.NAME);

        timerTextView = findViewById(R.id.timer_sekunden);
        videoView = findViewById(R.id.video_screen);
        startButton = findViewById(R.id.zaehne_Putzen);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Menu");
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }


        switch (holeselektiertesAlter) {
            case "3-12":
                setupKinderFunktionen();
                break;
            case "13-50":
                setupErwachseneFunktionen();
                break;
            case "50+":
                setupSeniorFunktionen();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            //error: constant expression required case R.id.zeige_data:

            case 1:

                Toast.makeText(this, "Show Data Clicked", Toast.LENGTH_SHORT).show();
                Intent dataIntent = new Intent(this, SensorDataActivity.class);
                startActivity(dataIntent);
                return true;

            case 2:

                Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void setupKinderFunktionen() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSensors();
                kinderFunktionen();
            }
        });
    }

    private void kinderFunktionen() {
        setupVideo("https://www.youtube.com/watch?v=XcC3IhE9nlQ");
        startTimer();
    }

    private void setupErwachseneFunktionen() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSensors();
                erwachseneFunktionen();
            }
        });
    }

    private void erwachseneFunktionen() {
        setupVideo("https://www.youtube.com/watch?v=XcC3IhE9nlQ");
        startTimer();
    }

    private void setupSeniorFunktionen() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSensors();
                seniorFunktionen();
            }
        });
    }

    private void seniorFunktionen() {
        setupVideo("https://www.youtube.com/watch?v=XcC3IhE9nlQ");
        startTimer();
    }

    private void setupVideo(String videoUrl) {
        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        videoView.setWebViewClient(new WebViewClient());
        String html = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        videoView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    private void startTimer() {
        new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Remaining time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.d("RealMainActivity", "Timer ended");
                timerTextView.setText("The time is over - Good Job");
                unregisterSensors();
            }
        }.start();
    }

    private void registerSensors() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregisterSensors() {
        sensorManager.unregisterListener(this);
    }

    private void showSensorData() {
        Intent intent = new Intent(this, SensorDataActivity.class);
        intent.putStringArrayListExtra("sensorData", sensorDataList);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            String data = "Accel - X: " + x + ", Y: " + y + ", Z: " + z;
            sensorDataList.add(data);
            Log.d("SensorData", data);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            String data = "Gyro - X: " + x + ", Y: " + y + ", Z: " + z;
            sensorDataList.add(data);
            Log.d("SensorData", data);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterSensors();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }


}
