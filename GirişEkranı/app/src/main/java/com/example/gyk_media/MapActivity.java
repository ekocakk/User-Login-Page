package com.example.gyk_media;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapActivity extends AppCompatActivity {

    private Button haritaGit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        haritaGit = findViewById(R.id.haritaGit);

        haritaGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri adres = Uri.parse("geo:41.0138400,28.9496600");
                adresiAc(adres);
            }
        });
    }

    public void adresiAc(Uri haritaAdresi){
        Intent haritaIntent = new Intent(Intent.ACTION_VIEW);
        haritaIntent.setData(haritaAdresi);

        if(haritaIntent.resolveActivity(getPackageManager()) != null){
            startActivity(haritaIntent);
        }
    }
}
