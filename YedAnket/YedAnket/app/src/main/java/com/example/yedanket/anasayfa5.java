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

public class anasayfa5 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btn,button,button1;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    Member member;
    DatabaseReference reference;
    int i = 0;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa5);

        btn = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.anasayfa5button2);
        button = (Button) findViewById(R.id.anasayfa5button1);

        mAuth = FirebaseAuth.getInstance();
        member = new Member();

        c1 = findViewById(R.id.checkbox51);
        c2 = findViewById(R.id.checkbox52);
        c3 = findViewById(R.id.checkbox53);
        c4 = findViewById(R.id.checkbox54);
        c5 = findViewById(R.id.checkbox55);
        c6 = findViewById(R.id.checkbox56);
        c7 = findViewById(R.id.checkbox57);
        c8 = findViewById(R.id.checkbox58);
        c9 = findViewById(R.id.checkbox59);
        c10 = findViewById(R.id.checkbox50);

        FirebaseUser kullanici = mAuth.getCurrentUser();
        String kullaniciId = kullanici.getUid();
        reference = database.getInstance().getReference().child("Kullanicilar").child(kullaniciId).child("İlk 10 Soru").child("5.Soru");

        final String d1 = c1.getText().toString();
        final String d2 = c2.getText().toString();
        final String d3 = c3.getText().toString();
        final String d4 = c4.getText().toString();
        final String d5 = c5.getText().toString();
        final String d6 = c6.getText().toString();
        final String d7 = c7.getText().toString();
        final String d8 = c8.getText().toString();
        final String d9 = c9.getText().toString();
        final String d10 =c10.getText().toString();

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), anasayfa4.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), anasayfa6.class);
                startActivity(intent);

                if (c1.isChecked()) {
                    member.setCh1(d1);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c2.isChecked()) {

                    member.setCh2(d2);
                    reference.child(String.valueOf(i + 1)).setValue(member);


                } else {

                }
                if (c3.isChecked()) {
                    member.setCh3(d3);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c4.isChecked()) {

                    member.setCh4(d4);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c5.isChecked()) {
                    member.setCh5(d5);
                    reference.child(String.valueOf(i + 1)).setValue(member);
                } else {

                }

                if (c6.isChecked()) {
                    member.setCh6(d6);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c7.isChecked()) {

                    member.setCh7(d7);
                    reference.child(String.valueOf(i + 1)).setValue(member);


                } else {

                }
                if (c8.isChecked()) {
                    member.setCh8(d8);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c9.isChecked()) {

                    member.setCh9(d9);
                    reference.child(String.valueOf(i + 1)).setValue(member);

                } else {

                }
                if (c10.isChecked()) {
                    member.setCh10(d10);
                    reference.child(String.valueOf(i + 1)).setValue(member);
                } else {

                }

            }

    });
    }
}