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




public class Anasayfa2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btn;
    FirebaseDatabase database;
    Button button,button1;
    CheckBox c1,c2,c3,c4,c5;
    Member member;
    DatabaseReference reference;
    int i = 0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa2);
        mAuth = FirebaseAuth.getInstance();
        btn = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.anasayfa2button2);
        button = (Button) findViewById(R.id.anasayfa2button);
        member = new Member();
        button = findViewById(R.id.anasayfa2button);
        c2 = findViewById(R.id.checkbox21);
        c1 = findViewById(R.id.checkbox22);
        c3 = findViewById(R.id.checkbox23);
        c4 = findViewById(R.id.checkbox24);
        c5 = findViewById(R.id.checkbox25);
        FirebaseUser kullanici = mAuth.getCurrentUser();
        String kullaniciId = kullanici.getUid();
        reference = database.getInstance().getReference().child("Kullanicilar").child(kullaniciId).child("İlk 10 Soru").child("2.Soru");

        final String d1 = c1.getText().toString();
        final String d2 = c2.getText().toString();
        final String d3 = c3.getText().toString();
        final String d4 = c4.getText().toString();
        final String d5 = c5.getText().toString();

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
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Oturum Kapatıldı !", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),AnasayfaActivity.class);
                startActivity(intent1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Anasayfa3Activity.class);
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
                if (c3.isChecked()){
                    member.setCh3(d3);
                    reference.child(String.valueOf(i+1)).setValue(member);

                }else {

                }
                if (c4.isChecked()){

                    member.setCh4(d4);
                    reference.child(String.valueOf(i+1)).setValue(member);

                }else {

                }
                if(c5.isChecked()){
                    member.setCh5(d5);
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                else {

                }

            }

        });



    }
}
