package com.example.cleansmile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    DrawerLayout menu;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        textView=findViewById(R.id.about_us_view);

        menu = findViewById(R.id.background_menu);
    }

    public void ClickOnMenu(View view){
        RealMainActivity.openMenu(menu);
    }

    public static void openMenu(DrawerLayout menu) {
        menu.openDrawer(GravityCompat.START);
    }

    public void LogoClick(View view){
        closeMenu(menu);
    }

    public static void closeMenu(DrawerLayout menu) {
        if(menu.isDrawerOpen(GravityCompat.START)){
            menu.closeDrawer(GravityCompat.START);
        }
    }

    public void MainPageClick(View view){
        recreate();
    }

    public void ShowDataClick(View view){
        Intent intent = new Intent(this,SensorDataActivity.class);
        startActivity(intent);

    }

    public void SetReminderClick(View view){
        Intent intent = new Intent(this,AddReminder.class);
        startActivity(intent);
    }

    public void AboutClick(View view){

        Toast.makeText(this,"Über uns-Seite !!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,AboutUsActivity.class);
        startActivity(intent);
    }

    public void ExitClick(View view){
        //About us Aktivität, werde morgen erstellen!
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(AboutUsActivity.this);
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