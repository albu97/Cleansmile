package com.example.cleansmile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

public class AlterWaehlenActivity extends AppCompatActivity {
    public static final String NAME ="com.example.cleansmile.extra.NAME";
    private FileUtils.ProgressListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_waehlen);

        Spinner spinner= findViewById(R.id.alter_waehlen);

        Button button =findViewById(R.id.button_submit);

        String []  alter = {"Select Age","3-12","13-50","50+"};

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.age_array, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selektiertesAlter = spinner.getSelectedItem().toString();

                if (selektiertesAlter.equals("Select Age")){

                    Log.e("AlterWaehlenActivity", "Please select your age!");
                   Snackbar.make(findViewById(R.id.button_submit), "Please select your age!", Snackbar.LENGTH_LONG).show();
                }
                else{

                    Intent intent = new Intent(AlterWaehlenActivity.this,RealMainActivity.class);

                    intent.putExtra(AlterWaehlenActivity.NAME,selektiertesAlter);

                    startActivity(intent);
                }

            }
        });

    }
    public void ageClass(FileUtils.ProgressListener listener) {
        this.listener = listener;}
}