package com.example.atozplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageMainActivity extends AppCompatActivity {

    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_main);


        Intent intent = getIntent();
        String url = intent.getStringExtra("image");

        imageView = findViewById(R.id.imageView);
        Uri uri = Uri.parse(url);
        imageView.setImageURI(uri);


//        try {
//
//            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(url));
//            imageView.setImageBitmap(bitmap);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        InputStream inputStream=null;
//
//
//        try {
//            assert url!=null;
//            inputStream=getContentResolver().openInputStream(Uri.parse(url));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BitmapFactory.decodeStream(inputStream);


    }
}