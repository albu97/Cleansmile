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
import android.webkit.JavascriptInterface;
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
    private WebView webView;
    private Button startButton;
    private ArrayList<String> sensorDataList = new ArrayList<>();
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Intent intent = getIntent();
        String holeselektiertesAlter = intent.getStringExtra(AlterWaehlenActivity.NAME);

        timerTextView = findViewById(R.id.timer_sekunden);
        webView = findViewById(R.id.video_screen);
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
                startTimer();
            }
        });
    }

    private void kinderFunktionen() {

        if (webView!=null){
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new WebAppInterface(), "Android");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                }
            });

            String videoUrl = "<html><body><iframe width=\"100%\" " +
                    "height=\"100%\" src=\"https://www.youtube.com/embed/XcC3IhE9nlQ?autoplay=1\" " +
                    "frameborder=\"0\" allowfullscreen></iframe></body></html>";

            webView.loadData(videoUrl, "text/html", "utf-8");
        }
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
                stopVideo();
                registerSensors();
            }
        }.start();
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

    private class WebAppInterface {
        @JavascriptInterface
        public void stopVideoFromJava() {
            stopVideo();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer !=null) {
            countDownTimer.cancel();
        }
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
        if (webView!=null){

            webView.loadUrl("https://www.youtube.com/watch?v=XcC3IhE9nlQ");
        }
       // startTimer();
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
        if (webView!=null){

            webView.loadUrl("https://www.youtube.com/watch?v=XcC3IhE9nlQ");
        }
       // startTimer();
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
