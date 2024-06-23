package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class RealMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Intent intent = getIntent();

        String holeselektiertesAlter=intent.getStringExtra(AlterWaehlenActivity.NAME);


            switch (holeselektiertesAlter){

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

    public void setupKinderFunktionen(){

        TextView timerTextView = findViewById(R.id.timer_sekunden);
        WebView videoView = findViewById(R.id.video_screen);
        Button startButton = findViewById(R.id.zaehne_Putzen);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kinderFunktionen(timerTextView,videoView);
            }
        });

    }

    public void kinderFunktionen(TextView timerTextView, WebView videoView){

        //+ Foto hinzufügen
        getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

        //wichtig: WebView to load a YouTube video
        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        videoView.setWebViewClient(new WebViewClient());
        String videoUrl = "https://www.youtube.com/watch?v=XcC3IhE9nlQ";
        videoView.loadUrl(videoUrl);


        new CountDownTimer(120000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText("Verbleibende Sekunden: " + millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {

                Log.d("RealMainActivity", "Timer beendet");
                timerTextView.setText("Die Zeit ist vorbei");

            }
        }.start();

       /* String video= "android.resource://" + getPackageName() + "/" + R.raw.brush_your_teeth;
        Uri uri = Uri.parse(video);
        videoView.setVideoURI(uri);
        videoView.start(); */

    }

    public void setupErwachseneFunktionen(){

        TextView timerTextView = findViewById(R.id.timer_sekunden);
        WebView videoView = findViewById(R.id.video_screen);
        Button startButton = findViewById(R.id.zaehne_Putzen);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                erwachseneFunktionen(timerTextView,videoView);
            }
        });

    }

    public void erwachseneFunktionen(TextView timerTextView, WebView videoView){

        //+ Foto hinzufügen
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);

        //wichtig: WebView to load a YouTube video
        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        videoView.setWebViewClient(new WebViewClient());
        String videoUrl = "https://www.youtube.com/watch?v=XcC3IhE9nlQ";
        videoView.loadUrl(videoUrl);

        new CountDownTimer(120000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText("Verbleibende Sekunden: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                Log.d("RealMainActivity", "Timer beendet");
                timerTextView.setText("Die Zeit ist vorbei");

            }

        }.start();

      /*  String video= "android.resource://" + getPackageName() + "/" + R.raw.brush_your_teeth;
        Uri uri = Uri.parse(video);
        videoView.setVideoURI(uri);
        videoView.start();*/
//chabge

    }


   public void setupSeniorFunktionen(){

       TextView timerTextView = findViewById(R.id.timer_sekunden);
       WebView videoView = findViewById(R.id.video_screen);
       Button startButton = findViewById(R.id.zaehne_Putzen);

       startButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               seniorFunktionen(timerTextView, videoView);
           }
       });


   }

    public void seniorFunktionen(TextView timerTextView,WebView videoView){

        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);


        //wichtig: WebView to load a YouTube video
        WebSettings webSettings = videoView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        videoView.setWebViewClient(new WebViewClient());
        String videoUrl = "https://www.youtube.com/watch?v=XcC3IhE9nlQ";
        videoView.loadUrl(videoUrl);

        new CountDownTimer(120000,1000){


            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText("Verbleibende Sekunden: " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                Log.d("RealMainActivity", "Timer beendet");
                timerTextView.setText("Die Zeit ist vorbei");

            }
        }.start();

      /*  String video= "android.resource://" + getPackageName() + "/" + R.raw.brush_your_teeth;
        Uri uri = Uri.parse(video);
        videoView.setVideoURI(uri);
        videoView.start();*/


        //Link hinzufügen
        //https://developer.android.com/studio/write/app-link-indexing?hl=de

    }
}