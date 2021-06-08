package com.example.yedanket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class anasayfa4 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btn,button,button1;
    CheckBox c1,c2,c3,c4,c5;
    Member member;
    int i = 0;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa4);
        btn = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.anasayfa4button2);
        button = (Button) findViewById(R.id.anasayfa4button1);
        mAuth = FirebaseAuth.getInstance();
        member = new Member();
        c2 = findViewById(R.id.checkbox41);
        c1 = findViewById(R.id.checkbox42);

        final String d1 = c1.getText().toString();
        final String d2 = c2.getText().toString();

        FirebaseUser kullanici = mAuth.getCurrentUser();
        String kullaniciId = kullanici.getUid();
        reference = database.getInstance().getReference().child("Kullanicilar").child(kullaniciId).child("İlk 10 Soru").child("4.Soru");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Oturum Kapatıldı !", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Anasayfa3Activity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),anasayfa5.class);
                startActivity(intent);


                if (c1.isChecked()){
                    member.setCh1(d1);
                    reference.child(String.valueOf(i+1)).setValue(member);

                }else {

                }
                if (c2.isChecked()){

                    member.setCh2(d2);
                    reference.child(String.valueOf(i+1)).setValue(member);


                }else {

                }
            }
        });
    }
}