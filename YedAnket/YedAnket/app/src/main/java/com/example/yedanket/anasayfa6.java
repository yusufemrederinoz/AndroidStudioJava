package com.example.yedanket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class anasayfa6 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btn,button,button1;
    Spinner spinner1;
    DatabaseReference reference;
    FirebaseDatabase database;

    private ArrayAdapter<String> dataAdapterForSektor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa6);

        btn = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.anasayfa6button2);
        button = (Button) findViewById(R.id.anasayfa6button1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        mAuth = FirebaseAuth.getInstance();

        final String sektor[] = {"1- Adalet ve Güvenlik","2- Ağaç İşleri, Kağıt ve Kağıt Ürünleri ",
                "3- Bilişim Teknolojileri ","4- Cam, Çimento ve Toprak ","5- Çevre"," 6- Eğitim ","7- Elektrik ve Elektronik ","8- Enerji"," 9- Finans ","10- Gıda ",
                "11- İnşaat ","12- İş ve Yönetim ","13- Kimya, Petrol, Lastik ve Plastik ",
                "14- Kültür, Sanat ve Tasarım"," 15- Maden ","16- Medya, İletişim ve Yayıncılık",
                " 17- Metal ","18- Otomotiv ","19- Sağlık ve Sosyal Hizmetler"," 20- Spor ve Rekreasyon ",
                "21- Tarım, Avcılık ve Balıkçılık ","22- Tekstil, Hazır Giyim, Deri",
                " 23- Ticaret(Satış ve Pazarlama) ","24- Toplumsal ve Kişisel Hizmetler ",
                "25- Turizm, Konaklama, Yiyecek-İçecek Hizmetleri"," 26- Ulaştırma, Lojistik ve Haberleşme"};

        dataAdapterForSektor= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sektor);
        dataAdapterForSektor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapterForSektor);

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
                Object a = spinner1.getSelectedItem();
                gonder(a);

            }
        });



    }

    private void gonder(Object a) {

        FirebaseUser kullanici = mAuth.getCurrentUser();
        String kullaniciId = kullanici.getUid();
        reference = database.getInstance().getReference().child("Kullanicilar").child(kullaniciId).child("İlk 10 Soru").child("6.Soru");
        reference.setValue(a).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),anasayfa7.class);
                    startActivity(intent);
                } else {

                }
            }
        });
    }
}