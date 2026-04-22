package com.example.icm_calculator.conseilsSante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.icm_calculator.R;

public class ConseilsSante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conseils_sante);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.conseilsSante), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Trouver la toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // 2. Activer le bouton de retour
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false); // Sécurité pour effacer le titre
        }

        // 3. Gérer le clic sur la flèche de retour
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourne à l'activité précédente (MainActivity)
                finish();
            }
        });

        // Redirection vers Perte de Poids
        Button btnPertePoids = findViewById(R.id.button2);
        btnPertePoids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConseilsSante.this, PertePoids.class);
                startActivity(intent);
            }
        });

        // Redirection vers Prise de Poids
        Button btnPrisePoids = findViewById(R.id.button3);
        btnPrisePoids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConseilsSante.this, PrisePoids.class);
                startActivity(intent);
            }
        });

        // Redirection vers Activité Sportive
        Button btnSport = findViewById(R.id.buttonSport);
        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConseilsSante.this, ActiviteSportif.class);
                startActivity(intent);
            }
        });
    }
}
