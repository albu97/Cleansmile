package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RealMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        Intent intent = getIntent();

        String holeselektiertesAlter=intent.getStringExtra(AlterWaehlenActivity.NAME);


        if(holeselektiertesAlter == null){
            System.out.println("Bitte wählen Sie ihr Alter aus !");

            switch (holeselektiertesAlter){

                case "0-13":
                    //Kinder Funktionen
      }
        }



    }
}