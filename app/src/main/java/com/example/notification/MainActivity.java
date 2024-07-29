package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Notify_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Notify_btn=findViewById(R.id.Notification_btn);

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.TIRAMISU)
        {
            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.POST_NOTIFICATIONS)!=(PackageManager.PERMISSION_GRANTED))
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        Notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

    }

    public void sendNotification()
    {
        String CHANNEL_ID="My_Channel_Id";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notifications_icon) // Required: Small icon
                .setContentTitle("Welcome Notification") // Title
                .setContentText("First sample notification") // Body text
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); // Notification priority

        Intent intent=new Intent(getApplicationContext(),MessageActivity.class);
        intent.putExtra("Message", "Full body of the message");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=notificationManager.getNotificationChannel(CHANNEL_ID);
            if(channel==null)
            {
                int importance=NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"Hello",importance);
               notificationChannel.setLightColor(Color.GREEN);
               notificationChannel.enableVibration(true);

               notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(1,builder.build());
    }
}