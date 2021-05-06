package com.example.gyk_media;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SmsActivity extends AppCompatActivity {

    private EditText telefon;
    private EditText mesaj;
    private Button smsGonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        telefon = findViewById(R.id.telefon);
        mesaj = findViewById(R.id.mesaj);
        smsGonder = findViewById(R.id.smsButton);

        smsGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gonderilecekMesaj = mesaj.getText().toString();
                String telefonNo = telefon.getText().toString();
                smsGonder(gonderilecekMesaj,telefonNo);
            }
        });
    }

    public void smsGonder(String gelenMesaj, String gelenTelefon){
        Uri uri = Uri.parse("smsto:" + gelenTelefon);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        intent.putExtra("sms_body",gelenMesaj);
        startActivity(intent);
    }
}
