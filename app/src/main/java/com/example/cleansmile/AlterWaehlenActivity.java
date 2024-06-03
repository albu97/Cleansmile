package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AlterWaehlenActivity extends AppCompatActivity {
    public static final String NAME ="com.example.cleansmile.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_waehlen);

        Spinner spinner= findViewById(R.id.alter_waehlen);

        Button button =findViewById(R.id.button_submit);

        String []  alter = {"0-12","13-50","50+"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,alter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlterWaehlenActivity.this,RealMainActivity.class);
                String selektiertesAlter = spinner.getSelectedItem().toString();

                intent.putExtra(AlterWaehlenActivity.NAME,selektiertesAlter);

                startActivity(intent);
            }
        });

    }
}