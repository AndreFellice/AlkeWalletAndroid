package com.example.alkewalletandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pantalla5Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla5_home);

        Button enviar = findViewById(R.id.btn8);
        Button recibir = findViewById(R.id.btn9);
        ImageView notificacion= findViewById(R.id.campana);
        ImageView perfil2 = findViewById(R.id.foto_cliente2);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla5Home.this, Pantalla8SendM.class) ;
                startActivity(intent);
            }
        });

        recibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla5Home.this, Pantalla9RequestM.class) ;
                startActivity(intent);
            }
        });

        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla5Home.this, Pantalla6Empty.class);
                startActivity(intent);
            }
        });

        perfil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla5Home.this, Pantalla7Profile.class);
                startActivity(intent);
            }
        });


        ImageView volver = findViewById(R.id.logo5);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}