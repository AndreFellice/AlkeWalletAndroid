package com.example.alkewalletandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alkewalletandroid.view.MainActivity;

public class Pantalla6Empty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla6_empty);


        Button enviar = findViewById(R.id.btn10);
        Button recibir = findViewById(R.id.btn11);
        ImageView perfil = findViewById(R.id.foto_cliente);



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla6Empty.this, Pantalla8SendM.class) ;
                startActivity(intent);
            }
        });

        recibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla6Empty.this, Pantalla9RequestM.class) ;
                startActivity(intent);
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla6Empty.this, Pantalla7Profile.class);
                startActivity(intent);
            }
        });


        ImageView volver = findViewById(R.id.logo6);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
