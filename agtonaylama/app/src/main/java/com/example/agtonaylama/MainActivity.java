package com.example.agtonaylama;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    TextView yoneticigirisi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        setContentView(R.layout.activity_main);
        yoneticigirisi = findViewById(R.id.yongir);

        yoneticigirisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,yoneticiactivity.class));
            }
        });


    }
}