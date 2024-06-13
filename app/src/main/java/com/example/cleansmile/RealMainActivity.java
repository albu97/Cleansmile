package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class RealMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Intent intent = getIntent();

        String holeselektiertesAlter=intent.getStringExtra(AlterWaehlenActivity.NAME);


        if(holeselektiertesAlter == null) {

            Log.e("RealMainActivity", "Bitte wählen Sie ihr Alter aus !");
            return;
        }

            switch (holeselektiertesAlter){

                case "0-12":

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
        VideoView videoView = findViewById(R.id.video_screen);
        Button startButton = findViewById(R.id.zaehne_Putzen);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kinderFunktionen(timerTextView,videoView);
            }
        });

    }

    public void kinderFunktionen(TextView timerTextView, VideoView videoView){

        //+ Foto hinzufügen
        getWindow().getDecorView().setBackgroundColor(Color.YELLOW);


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
        VideoView videoView = findViewById(R.id.video_screen);
        Button startButton = findViewById(R.id.zaehne_Putzen);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                erwachseneFunktionen(timerTextView,videoView);
            }
        });

    }

    public void erwachseneFunktionen(TextView timerTextView, VideoView videoView){

        //+ Foto hinzufügen
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);


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
       VideoView videoView = findViewById(R.id.video_screen);
       Button startButton = findViewById(R.id.zaehne_Putzen);

       startButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               seniorFunktionen(timerTextView, videoView);
           }
       });


   }

    public void seniorFunktionen(TextView timerTextView,VideoView videoView){

        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);

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