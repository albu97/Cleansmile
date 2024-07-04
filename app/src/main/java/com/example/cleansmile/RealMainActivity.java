package com.example.cleansmile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class RealMainActivity extends AppCompatActivity implements SensorEventListener {

    public static final String NAME = "com.example.cleansmile.extra.NAME";
    DrawerLayout menu;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private TextView timerTextView;
    private WebView webView;

    private CountDownTimer countDown;
    private Button startButton;

    private Button stopButton;
    private Button resumeB;
    private long timeRemaining;

    private static final ArrayList<String> sensorDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);


        CameraSensor sensorCamera = new CameraSensor(this);
        sensorCamera.requestPermissions();

        Intent intent = getIntent();
        String holeselektiertesAlter = intent.getStringExtra(AlterWaehlenActivity.NAME);

        timerTextView = findViewById(R.id.timer_sekunden);
        webView = findViewById(R.id.video_screen);
        startButton = findViewById(R.id.zaehne_Putzen);
        stopButton=findViewById(R.id.stopButton);
        resumeB=findViewById(R.id.resumeButton);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        menu = findViewById(R.id.background_menu);


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
    public void ClickOnMenu(View view) {
        RealMainActivity.openMenu(menu);
    }

    public static void openMenu(DrawerLayout menu) {
        menu.openDrawer(GravityCompat.START);
    }

    public void LogoClick(View view) {
        closeMenu(menu);
    }

    public static void closeMenu(DrawerLayout menu) {
        if (menu.isDrawerOpen(GravityCompat.START)) {
            menu.closeDrawer(GravityCompat.START);
        }
    }

    public void MainPageClick(View view) {
        recreate();
    }

    public void ShowDataClick(View view) {

        showSensorData();
    }

    public void SetReminderClick(View view) {
        Intent intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }

    public void AboutClick(View view) {
        Toast.makeText(this,getString(R.string.about_us_toast), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,AboutUsActivity.class);
        startActivity(intent);
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(RealMainActivity.this);
        warningWindow.setTitle(R.string.exit_title);
        warningWindow.setMessage(R.string.exit_message);

        warningWindow.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });

        warningWindow.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
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
                Log.d("KinderFunktionen", getString(R.string.log_kinder_sta_b_click));
                registerSensors();
                kinderFunktionen();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KinderFunktionen", getString(R.string.log_kinder_sto_b_click));
                stopTimer();
            }
        });
        resumeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KinderFunktionen", getString(R.string.log_kinder_res_b_click));
                resumeTimer();
                registerSensors();
            }
        });
    }

    private void kinderFunktionen() {

        String videoUrl = "https://www.youtube.com/embed/wCio_xVlgQ0";
        playVideo(videoUrl);
        startTimer(140000);
    }

    private void setupErwachseneFunktionen() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ErwachseneFunktionen", getString(R.string.log_erwachs_sta_b_click));
                registerSensors();
                erwachseneFunktionen();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ErwachseneFunktionen", getString(R.string.log_erwachs_sto_b_click));
                stopTimer();
            }
        });
        resumeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ErwachseneFunktionen", getString(R.string.log_erwachs_res_b_click));
                resumeTimer();
                registerSensors();
            }
        });
    }

    private void erwachseneFunktionen() {

        String videoUrl = "https://www.youtube.com/embed/XcC3IhE9nlQ";
        playVideo(videoUrl);
        startTimer(140000);
    }

    private void setupSeniorFunktionen() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SeniorFunktionen", getString(R.string.log_senior_res_b_click));
                registerSensors();
                seniorFunktionen();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SeniorFunktionen", getString(R.string.log_senior_res_b_click));
                stopTimer();
            }
        });
        resumeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SeniorFunktionen", getString(R.string.log_senior_res_b_click));
                resumeTimer();
                registerSensors();
            }
        });
    }

    private void seniorFunktionen() {

        String videoUrl = "https://www.youtube.com/embed/gAODutgIIVQ&t=20s";
        playVideo(videoUrl);
        startTimer(140000);
    }

    private void startTimer(long duration) {

        countDown = new CountDownTimer(duration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                timerTextView.setText(getString(R.string.remaining_time) + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                stopVideo();
                Log.d("RealMainActivity", getString(R.string.log_end_timer));
                timerTextView.setText(getString(R.string.timer_over));
                registerSensors();

            }
        }.start();
    }

    public void stopTimer() {
        if (countDown != null) {
            countDown.cancel();
        }
    }

    public void resumeTimer(){
        startTimer(timeRemaining);
        registerSensors();
    }


    private void playVideo(String videoUrl) {
        if (webView != null) {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
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


    public void realMainClass(FileUtils.ProgressListener listener) {
    }
}

