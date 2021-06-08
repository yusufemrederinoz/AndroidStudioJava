package com.example.agtonaylama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class yoneticipanelactivity extends AppCompatActivity {
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoneticipanelactivity);
        button = (Button) findViewById(R.id.bildirim);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(yoneticipanelactivity.this);
                builder.setContentTitle("Bildirim");
                builder.setContentText("Deneme 1-2-3");
                builder.setSmallIcon(R.drawable.kamyon2);
                builder.setAutoCancel(true);
                builder.setTicker("Bildirim Geliyor");

                Intent intent = new Intent(yoneticipanelactivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(yoneticipanelactivity.this,1,intent,0);
                builder.setContentIntent(pendingIntent);
                Notification notification = builder.getNotification();
                manager.notify(1,notification);
            }

        });

    }

}