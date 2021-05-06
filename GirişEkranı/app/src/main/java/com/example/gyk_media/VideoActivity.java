package com.example.gyk_media;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button videoCek;
    private Button videoIzle;
    private VideoView videoView;
    private int keyVideo = 30;
    private Uri videoAdres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoCek = findViewById(R.id.videoCek);
        videoIzle = findViewById(R.id.videoIzle);
        videoView = findViewById(R.id.videoView);

        videoCek.setOnClickListener(this);
        videoIzle.setOnClickListener(this);

        videoIzle.setEnabled(false);
}

    @Override
    public void onClick(View v) {
            if(v == videoCek){
            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(videoIntent,keyVideo);
        }else if(v == videoIzle){
              videoView.setVideoURI(videoAdres);
              videoView.start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(this.keyVideo == requestCode && resultCode == RESULT_OK){
            videoAdres = data.getData();
            videoIzle.setEnabled(true);
        }
    }
}
