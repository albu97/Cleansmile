package com.example.cleansmile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {
    private View contentView;
    private DrawerLayout menu;
    private MyDatabaseHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        menu = findViewById(R.id.background_menu);
        Button logButton = findViewById(R.id.LogButton);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText username = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);
        Button newUser = findViewById(R.id.newUser);
        TextView textZoom = findViewById(R.id.textZoom);

        // Initialize Database Helper
        myHelper = new MyDatabaseHelper(this);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxScreenSize = Math.max(size.x, size.y);
        zoom.setMax(maxScreenSize);

        // Set up login button click listener
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String passWord = password.getText().toString();

                if (myHelper.isValidUser(user, passWord)) {
                    Intent intent = new Intent(getApplicationContext(), AlterWaehlenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong password or username", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set up new user button click listener
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String passWord = password.getText().toString();

                if (!myHelper.userExists(user)) {
                    myHelper.addUser(user, passWord);
                    Intent intent = new Intent(getApplicationContext(), AlterWaehlenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set up zoom seek bar change listener
        zoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float textSize = progress * 1.5f;
                textZoom.setTextSize(textSize);
                logButton.setTextSize(textSize);
                username.setTextSize(textSize);
                password.setTextSize(textSize);
                newUser.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No action needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No action needed
            }
        });
    }

    public void ClickOnMenu(View view) {
        RealMainActivity.openMenu(menu);
    }

    public void LogoClick(View view) {
        RealMainActivity.closeMenu(menu);
    }

    public void MainPageClick(View view) {
        recreate();
    }

    public void ShowDataClick(View view) {
        Toast.makeText(this, "You have to log in!", Toast.LENGTH_SHORT).show();
    }

    public void SetReminderClick(View view) {
        Intent intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }

    public void AboutClick(View view) {
        Toast.makeText(this, "Über uns-Seite !!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StartActivity.this, AddReminder.class);
        startActivity(intent);
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(StartActivity.this);
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
}