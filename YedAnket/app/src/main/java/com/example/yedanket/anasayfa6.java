package com.example.yedanket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class anasayfa6 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btn,button,button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa6);

        btn = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.anasayfa6button2);
        button = (Button) findViewById(R.id.anasayfa6button1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Oturum Kapatıldı !", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), anasayfa5.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),anasayfa7.class);
                startActivity(intent);
            }
        });

    }
}