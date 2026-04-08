package com.example.icm_calculator.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.icm_calculator.R;

public class ConnexionUtilisateur extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connexion_utilisateur);

        // 1. CONFIGURATION DE LA TOOLBAR
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Activer le bouton retour (la flèche)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Espace Membre");
        }

        // Gérer le clic sur la flèche de retour
        toolbar.setNavigationOnClickListener(v -> finish());

        // 2. GESTION DES SYSTEM BARS (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Redirection vers Prise de Poids
        Button btnPrisePoids = findViewById(R.id.login_button2);
        btnPrisePoids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionUtilisateur.this, InscriptionUtilisateur.class);
                startActivity(intent);
            }
        });
    }
}