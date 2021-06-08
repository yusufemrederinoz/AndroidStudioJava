package com.example.yedanket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SizeF;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class KayitActivity extends AppCompatActivity {
    EditText edt_kullaniciAdi,edt_Email,edt_Sifre;
    Button btn_KayitOl;
    TextView txt_GirisSayfasinaGit;

    FirebaseAuth yetki;
    DatabaseReference yol;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        edt_kullaniciAdi = findViewById(R.id.edt_kullanici_adi);
        edt_Email = findViewById(R.id.edt_email);
        edt_Sifre = findViewById(R.id.edt_sifre);
        btn_KayitOl = findViewById(R.id.btn_kayit_activity);
        txt_GirisSayfasinaGit = findViewById(R.id.GirisSayfasi_git);

        yetki = FirebaseAuth.getInstance();



        txt_GirisSayfasinaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KayitActivity.this,GirisActivity.class));
            }
        });

        btn_KayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd=new ProgressDialog(KayitActivity.this);
                pd.setMessage("Lütfen Bekleyin");
                pd.show();

                String str_kullaniciAdi = edt_kullaniciAdi.getText().toString();
                String str_email = edt_Email.getText().toString();
                String str_sifre = edt_Sifre.getText().toString();

                if(TextUtils.isEmpty(str_kullaniciAdi)||TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_sifre)) {
                    Toast.makeText(KayitActivity.this, "Lütfen Bütün Alanları Doldurun", Toast.LENGTH_SHORT).show();
                }
                else if (str_sifre.length()<6){
                    Toast.makeText(KayitActivity.this, "Şifreniz Minumum 6 Karakter Olmalı", Toast.LENGTH_SHORT).show();
                }
                else {
                    kaydet(str_kullaniciAdi,str_email,str_sifre);

                }

            }
        });

    }
    private void kaydet(final String kullaniciadi, final String email, final String sifre)
    {
        yetki.createUserWithEmailAndPassword(email,sifre)
                .addOnCompleteListener(KayitActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            FirebaseUser firebaseKullanici = yetki.getCurrentUser();


                            String kullaniciId = firebaseKullanici.getUid();


                            yol = FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(kullaniciId);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",kullaniciId);
                            hashMap.put("email",email);
                            hashMap.put("Sifre",sifre);
                            hashMap.put("kullaniciadi",kullaniciadi.toLowerCase());


                            yol.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        pd.dismiss();

                                        Intent intent = new Intent(KayitActivity.this,AnasayfaActivity.class);

                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                        startActivity(intent);

                                    }

                                }
                            });

                        }

                        else
                            {
                                pd.dismiss();
                                Toast.makeText(KayitActivity.this, "Bu mail veya şifre ile kayıt başarısız", Toast.LENGTH_LONG).show();

                        }




                    }
                });

    }
}