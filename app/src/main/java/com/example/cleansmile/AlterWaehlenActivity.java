package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class AlterWaehlenActivity extends AppCompatActivity {
    public static final String NAME ="com.example.cleansmile.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_waehlen);

        Spinner spinner= findViewById(R.id.alter_waehlen);

        Button button =findViewById(R.id.button_submit);

        String []  alter = {" ","3-12","13-50","50+"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,alter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selektiertesAlter = spinner.getSelectedItem().toString();

                if (selektiertesAlter.equals(" ")){

                    Log.e("AlterWaehlenActivity", "Bitte wählen Sie ihr Alter aus !");
                   Snackbar.make(findViewById(R.id.button_submit), "Bitte wählen Sie ihr Alter aus!", Snackbar.LENGTH_LONG).show();
                }
                else{

                    Intent intent = new Intent(AlterWaehlenActivity.this,RealMainActivity.class);

                    intent.putExtra(AlterWaehlenActivity.NAME,selektiertesAlter);

                    startActivity(intent);
                }

            }
        });

    }
}