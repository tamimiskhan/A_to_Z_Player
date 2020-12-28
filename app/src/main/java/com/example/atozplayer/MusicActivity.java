package com.example.atozplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent intent = getIntent();
        String url = intent.getStringExtra("music");

        //First work from raw file impurt music
        //  mediaPlayer =new MediaPlayer().create(MusicActivity.this,R.raw.music1);
        //  mediaPlayer.start();

        //second link from firebase ;
        //  String url="https://firebasestorage.googleapis.com/v0/b/enrich-58fdf.appspot.com/o/xxx.mp3?alt=media&token=aabcd430-9d46-45f6-ad21-fdca0895123f";


        mediaPlayer = new MediaPlayer();

        mediaPlayer.setAudioAttributes(new
                AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()

        );

         Uri uri=Uri.parse(url);
        try {
           // mediaPlayer.setDataSource(url);
            mediaPlayer.setDataSource(MusicActivity.this,uri);
            mediaPlayer.prepare();
             mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void play(View view) {
        mediaPlayer.start();

    }

    public void pause(View view) {
        mediaPlayer.pause();

    }

    public void stop(View view) {
        mediaPlayer.stop();

    }

    @Override
    public void onBackPressed() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();

        }
        super.onBackPressed();
    }
}