package com.example.icm_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. On lie le bouton du XML au code Java
        Button monBouton = findViewById(R.id.change_page);

        // 2. On écoute le clic sur le bouton
        monBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. On crée l'Intent pour aller de MainActivity vers Connexion
                Intent intent = new Intent(MainActivity.this, Connexion.class);
                startActivity(intent);
            }
        });
    }
}