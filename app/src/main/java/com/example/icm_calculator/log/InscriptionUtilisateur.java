package com.example.icm_calculator.log;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.icm_calculator.R;

public class InscriptionUtilisateur extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_incription_utilisateur);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_inscription), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialiser la Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar_inscription);
        setSupportActionBar(toolbar);

        // Activer le bouton retour
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Faire fonctionner le bouton retour
        toolbar.setNavigationOnClickListener(v -> finish());


    }


}
