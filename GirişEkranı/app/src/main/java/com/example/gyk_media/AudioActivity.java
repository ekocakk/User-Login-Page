package com.example.gyk_media;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button kaydetButton;
    private Button durdurButton;
    private Button oynatButton;

    private MediaRecorder recorder;
    private MediaPlayer player;
    private final String path = Environment.getExternalStorageDirectory().getPath() + "/gyk.3gp";


    private final int REQUEST_AUDIO_PERMISSION_CODE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        kaydetButton = findViewById(R.id.kaydet);
        durdurButton = findViewById(R.id.durdur);
        oynatButton = findViewById(R.id.oynat);

        kaydetButton.setOnClickListener(this);
        durdurButton.setOnClickListener(this);
        oynatButton.setOnClickListener(this);

        durdurButton.setVisibility(View.GONE);
        oynatButton.setVisibility(View.GONE);

        izinIste();
    }

    @Override
    public void onClick(View v) {

        if(v == kaydetButton){
            if(izinVarmi()){
                kayitBaslat();
            }else{
                Toast.makeText(this,"Mikrofon izni vermediğiniz için kayıt başlatılamadı..",Toast.LENGTH_SHORT).show();
            }

        }else if(v == durdurButton){
            kayitDurdur();

        }else if (v == oynatButton){
            kayitOynat();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "İzin verildi.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"İzin verilmedi.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public void kayitBaslat(){

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(path);

        kaydetButton.setVisibility(View.GONE);
        durdurButton.setVisibility(View.VISIBLE);


        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(this,"Kayıt başladı..",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void kayitDurdur(){
        if(recorder != null){
            Toast.makeText(this,"Kayıt Durduruldu",Toast.LENGTH_SHORT).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;

            oynatButton.setVisibility(View.VISIBLE);
            durdurButton.setVisibility(View.GONE);
        }

    }

    public void kayitOynat(){
        player = new MediaPlayer();
        player.setVolume(1.0f,1.0f);

        try {
            player.setDataSource(path);
            player.prepare();
            player.start();
            Toast.makeText(this,"Kayıt çalınıyor..",Toast.LENGTH_SHORT).show();

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    player.release();
                    player = null;

                    kaydetButton.setVisibility(View.VISIBLE);
                    oynatButton.setVisibility(View.GONE);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void izinIste(){
        ActivityCompat.requestPermissions(AudioActivity.this, new String[]{RECORD_AUDIO,WRITE_EXTERNAL_STORAGE},REQUEST_AUDIO_PERMISSION_CODE );
    }
    public boolean izinVarmi(){

        int result = ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }
}
