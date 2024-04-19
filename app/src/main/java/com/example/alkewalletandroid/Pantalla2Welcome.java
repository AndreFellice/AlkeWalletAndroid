package com.example.alkewalletandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;


public class Pantalla2Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2_welcome);

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