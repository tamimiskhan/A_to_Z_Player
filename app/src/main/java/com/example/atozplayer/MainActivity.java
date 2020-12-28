package com.example.atozplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int music_result = 1;
    final static int IMAGE_Result = 2;
    ImageButton music_button, video_button, image_button;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music_button = findViewById(R.id.music_button);
        video_button = findViewById(R.id.video_button);
        image_button = findViewById(R.id.image_button);

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_menu:


                        break;
                    case R.id.music_menu:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("audio/*");
                        startActivityForResult(intent, music_result);


                        break;
                    case R.id.video_menu:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));

                        break;
                }
                return true;
            }
        });


        music_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                startActivityForResult(intent, music_result);


            }
        });


        video_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this, VideoActivity.class));


            }
        });

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"), IMAGE_Result);


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_Result);




            }
        });

        requestPermission();

    }

    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == music_result) {

            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Uri uri = data.getData();
                    String musicUrl = String.valueOf(uri);

                    Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                    intent.putExtra("music", musicUrl);
                    startActivity(intent);


                }

            }

        }
        if (requestCode == IMAGE_Result) {

            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Uri uri = data.getData();
                    String imageUrl = String.valueOf(uri);

                   // Bitmap imageUrl = (Bitmap) data.getExtras().get("data");

                    Intent intent = new Intent(getApplicationContext(), ImageMainActivity.class);
                    intent.putExtra("image", imageUrl);
                    startActivity(intent);


                }


            }

        }
    }
}