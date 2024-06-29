package com.example.cleansmile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity {

    DrawerLayout menu;
    private TimePicker timePicker;
    private EditText messageEditText;
    private Button reminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        timePicker = findViewById(R.id.time_picker);
        messageEditText = findViewById(R.id.message_edit_text);
        reminderButton = findViewById(R.id.addReminderButton);

        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String message = messageEditText.getText().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (!NotificationManagerCompat.from(AddReminder.this).areNotificationsEnabled()) {
                        ActivityCompat.requestPermissions(AddReminder.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 123);
                        return;
                    }
                }


                Intent intent = new Intent(AddReminder.this, ReminderReceiver.class);
                intent.putExtra("message", message);


                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddReminder.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getTriggerTime(hour, minute), AlarmManager.INTERVAL_DAY, pendingIntent);

                Toast.makeText(AddReminder.this, R.string.reminder_set + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private long getTriggerTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String message = messageEditText.getText().toString();

                Intent intent = new Intent(AddReminder.this, ReminderReceiver.class);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddReminder.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getTriggerTime(hour, minute), AlarmManager.INTERVAL_DAY, pendingIntent);

                Toast.makeText(AddReminder.this, R.string.reminder_set + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ClickOnMenu(View view){
        openMenu(menu);
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
       //showSensorData();
    }

    public void SetReminderClick(View view){
        Intent intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }

    public void AboutClick(View view){
        //About us Aktivität, werde morgen erstellen!
        Toast.makeText(this,"Über uns-Seite !!!!",Toast.LENGTH_SHORT).show();
    }

    public void ExitClick(View view){
        //About us Aktivität, werde morgen erstellen!
        AlertDialog.Builder warningWindow = new AlertDialog.Builder(AddReminder.this);
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