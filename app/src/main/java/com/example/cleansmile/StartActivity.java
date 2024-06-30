package com.example.cleansmile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
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


        menu = findViewById(R.id.background_menu);
        Button logButton = findViewById(R.id.LogButton);
        SeekBar zoom = findViewById(R.id.zoom);
        EditText username = findViewById(R.id.User);
        EditText password = findViewById(R.id.Password);
        Button newUser = findViewById(R.id.newUser);
        TextView textZoom = findViewById(R.id.textZoom);


        myHelper = new MyDatabaseHelper(this);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxScreenSize = Math.max(size.x, size.y);
        zoom.setMax(maxScreenSize);


        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String passWord = password.getText().toString();

                if (myHelper.isValidUser(user, passWord)) {
                    Intent intent = new Intent(getApplicationContext(), AlterWaehlenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.wrong_pass_name, Toast.LENGTH_LONG).show();
                }
            }
        });


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
                    Toast.makeText(getApplicationContext(), R.string.user_exists, Toast.LENGTH_LONG).show();
                }
            }
        });

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

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void ClickOnMenu(View view) {
        RealMainActivity.openMenu(menu);
    }

    public void LogoClick(View view) {
        RealMainActivity.closeMenu(menu);
    }

    public static void closeMenu(DrawerLayout menu) {
        if(menu.isDrawerOpen(GravityCompat.START)){
            menu.closeDrawer(GravityCompat.START);
        }
    }

    public void MainPageClick(View view) {
        recreate();
    }

    public void ShowDataClick(View view) {
        Toast.makeText(this, R.string.log_in_toast, Toast.LENGTH_SHORT).show();
    }

    public void SetReminderClick(View view) {
        Toast.makeText(this, R.string.log_in_toast, Toast.LENGTH_SHORT).show();
    }

    public void AboutClick(View view) {
        Toast.makeText(this, R.string.about_us_toast, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StartActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(StartActivity.this);
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
}