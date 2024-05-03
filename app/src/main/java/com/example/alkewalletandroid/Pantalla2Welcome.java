package com.example.alkewalletandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Pantalla2Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla2_welcome);
        VideoView videoView = findViewById(R.id.videoView2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video2;
        videoView.setVideoURI(Uri.parse(videoPath));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        Button registrar = findViewById(R.id.btn1);
        Button logear = findViewById(R.id.btn2);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla2Welcome.this, Pantalla4Signup.class) ;
                startActivity(intent);
            }
        });

        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla2Welcome.this, Pantalla3Login.class) ;
                startActivity(intent);
            }
        });





        ImageView volver = findViewById(R.id.logo2);
          volver.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(intent);
              }
          });


    }
}