package com.example.agtonaylama;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class yoneticiactivity extends AppCompatActivity {
    EditText edt_emailgiris, edt_sifregiris;
    Button btn_giris_yap;
    FirebaseAuth girisYetkisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoneticiactivity);


        edt_emailgiris = findViewById(R.id.edt_email_giris);
        edt_sifregiris = findViewById(R.id.edt_sifre_giris);

        btn_giris_yap = findViewById(R.id.btn_giris_activity);

        girisYetkisi = FirebaseAuth.getInstance();

        btn_giris_yap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog pdGiris = new ProgressDialog(yoneticiactivity.this);
                pdGiris.setMessage("Giriş Yapılıyor...");
                pdGiris.show();

                String str_emailGiris = edt_emailgiris.getText().toString();
                String str_sifreGiris = edt_sifregiris.getText().toString();

                if (TextUtils.isEmpty(str_emailGiris) || TextUtils.isEmpty(str_sifreGiris)) {
                    Toast.makeText(yoneticiactivity.this, "Lütfen Bütün Alanları Doldurunuz", Toast.LENGTH_LONG).show();
                } else {
                    girisYetkisi.signInWithEmailAndPassword(str_emailGiris, str_sifreGiris)
                            .addOnCompleteListener(yoneticiactivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        DatabaseReference yolGiris = FirebaseDatabase.getInstance().getReference().child("Kullanicilar").child(girisYetkisi.getCurrentUser().getUid());
                                        yolGiris.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                pdGiris.dismiss();
                                                Intent intent = new Intent(getApplicationContext(), yoneticipanelactivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                pdGiris.dismiss();

                                            }
                                        });
                                    } else {
                                        pdGiris.dismiss();
                                        Toast.makeText(yoneticiactivity.this, "Giris Başarısız oldu!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
            }
        });


    }
}