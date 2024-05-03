package com.example.alkewalletandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        VideoView videoView = findViewById(R.id.videoView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        videoView.setVideoURI(Uri.parse(videoPath));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        ImageView next= findViewById(R.id.logo);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Pantalla2Welcome.class) ;
                startActivity(intent);
            }
        });
    }


}