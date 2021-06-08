package com.example.yedanket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btn_baslangicGiris;
    Button btn_baslangicKayitol;

    FirebaseUser baslangicKullanici;

    @Override
    protected void onStart() {
        super.onStart();

        baslangicKullanici = FirebaseAuth.getInstance().getCurrentUser();

        if(baslangicKullanici!= null){

            startActivity(new Intent(MainActivity.this,AnasayfaActivity.class));
            finish();

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_baslangicGiris = findViewById(R.id.btn_giris);
        btn_baslangicKayitol = findViewById(R.id.btn_kayit);

        btn_baslangicGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,GirisActivity.class));
            }
        });

        btn_baslangicKayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,KayitActivity.class));
            }
        });
    }
}