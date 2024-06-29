package com.example.cleansmile;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.FileUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class ReminderReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.example.cleansmile.reminder_channel";
    private FileUtils.ProgressListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");

        // Create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Reminder Channel";
            String description = "Channel for reminder notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.baseline_circle_notifications_24);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.baseline_doorbell_24));
        builder.setContentTitle("Time to brush your teeth!");
        builder.setContentText(message);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED) {

                return;
            }
        }

        notificationManager.notify(12345, builder.build());
    }
    public void reminderReceiverclass(FileUtils.ProgressListener listener) {
        this.listener = listener;}
}