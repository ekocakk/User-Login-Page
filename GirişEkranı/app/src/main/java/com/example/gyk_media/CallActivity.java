package com.example.gyk_media;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CallActivity extends AppCompatActivity {

    private EditText telefon;
    private Button araButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        telefon = findViewById(R.id.phone);
        araButton = findViewById(R.id.araButton);

        araButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = telefon.getText().toString();
                ara(tel);
            }
        });
    }

    public void ara(String telefon){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telefon));

        startActivity(intent);
    }
}
