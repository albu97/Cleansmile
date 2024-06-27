package com.example.cleansmile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RealMainActivity extends AppCompatActivity implements SensorEventListener {

    DrawerLayout menu;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private TextView timerTextView;
    private WebView webView;
    private Button startButton;
    private ArrayList<String> sensorDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        menu= findViewById(R.id.background_menu);

        Intent intent = getIntent();
        String holeselektiertesAlter = intent.getStringExtra(AlterWaehlenActivity.NAME);

        timerTextView = findViewById(R.id.timer_sekunden);
        webView = findViewById(R.id.video_screen);
        startButton = findViewById(R.id.zaehne_Putzen);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


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

    public void ClickOnMenu(View view){
        openMenu(menu);
    }

    public static void openMenu(DrawerLayout menu) {
        menu.openDrawer(GravityCompat.START);
    }

    public void LogoClick(View view){
        closeMenu(menu);
   }

    public static void closeMenu(DrawerLayout menu) {
        if(menu.isDrawerOpen(GravityCompat.START)){
            menu.closeDrawer(GravityCompat.START);
        }
    }

    public void MainPageClick(View view){
        recreate();
    }

    public void ShowDataClick(View view){
        showSensorData();
    }

    public void AboutClick(View view){
        //About us Aktivität, werde morgen erstellen!
        Toast.makeText(this,"Über uns-Seite !!!!",Toast.LENGTH_SHORT).show();
    }

    public void ExitClick(View view){
        //About us Aktivität, werde morgen erstellen!
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(RealMainActivity.this);
        warningWindow.setTitle("Exit");
        warningWindow.setMessage("Are you sure you want to exit?");

        warningWindow.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });

        warningWindow.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        warningWindow.show();
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

        String videoUrl = "https://www.youtube.com/embed/wCio_xVlgQ0";
        playVideo(videoUrl);
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

        String videoUrl = "https://www.youtube.com/embed/XcC3IhE9nlQ";
        playVideo(videoUrl);
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

        String videoUrl = "https://www.youtube.com/embed/gAODutgIIVQ&t=20s";
        playVideo(videoUrl);
        startTimer();
    }

    private void startTimer() {
        new CountDownTimer(140000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(getString(R.string.remaining_time) + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.d("RealMainActivity", "Timer ended");
                timerTextView.setText(getString(R.string.timer_over));
                stopVideo();
                registerSensors();
            }
        }.start();
    }

    private void playVideo(String videoUrl) {
        if (webView != null) {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {

                }
            });

            String htmlTemplate = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl +
                    "?autoplay=1\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

            webView.loadData(htmlTemplate, "text/html", "utf-8");
        }

    }
    private void stopVideo() {
        if (webView != null) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("(function() { " +
                            "var iframe = document.querySelector('iframe'); " +
                            "var video = iframe.contentWindow.document.querySelector('video'); " +
                            "if (video) { video.pause(); } })();", null);
                }
            });
        }
    }

    private void registerSensors() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregisterSensors() {
        sensorManager.unregisterListener(this);
    }

    public void showSensorData() {

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

        closeMenu(menu);
        super.onPause();
        unregisterSensors();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

}
