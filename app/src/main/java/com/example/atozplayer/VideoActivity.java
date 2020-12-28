package com.example.atozplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView=findViewById(R.id.videoView);
      progressBar=findViewById(R.id.progressBar);

       String videoPath="https://firebasestorage.googleapis.com/v0/b/lol-videos-8dc74.appspot.com/o/Blog_Images%2Fvideo%3A10142?alt=media&token=9f7734fa-f714-4838-bd65-8a4d594ec2ce";
        //Uri uri=Uri.parse(videoPath);

        videoView.setVideoPath(videoPath);

        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {


                mp.start();
                progressBar.setVisibility(View.GONE);


            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                progressBar.setVisibility(View.GONE);
            }
        });


    }

}