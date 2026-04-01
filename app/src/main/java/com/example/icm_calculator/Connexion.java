package com.example.icm_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Ajoute "extends AppCompatActivity" pour qu'Android comprenne que c'est une page
public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.connexion);

        // 1. Liaison des vues
        Button monBouton = findViewById(R.id.Calcule);
        EditText editPrenom = findViewById(R.id.editPoids);
        TextView textRemplire = findViewById(R.id.Remplire);

        // 2. Récupération au démarrage (Persistance)
        SharedPreferences sharedPref = getSharedPreferences("MesInfos", MODE_PRIVATE);
        String nomEnregistre = sharedPref.getString("prenom", "");

        if (!nomEnregistre.isEmpty()) {
            textRemplire.setText("Content de vous revoir, " + nomEnregistre);
            editPrenom.setText(nomEnregistre); // On remplit aussi le champ pour qu'il n'ait pas à retaper
        }

        // 3. Écouteur de la touche Entrée
        editPrenom.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {
                // On ne réagit que si c'est la touche ENTREE et qu'on vient d'appuyer dessus
                if ((event.getAction() == android.view.KeyEvent.ACTION_DOWN) &&
                        (keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {

                    String prenomSaisi = editPrenom.getText().toString();

                    // --- SAUVEGARDE UNIQUEMENT ICI ---
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("prenom", prenomSaisi);
                    editor.apply();

                    // Affichage
                    textRemplire.setText("Bonjour " + prenomSaisi + " !");

                    return true;
                }
                return false;
            }
        });

        // 4. Passage à la page suivante
        monBouton.setOnClickListener(v -> {
            Intent intent = new Intent(Connexion.this, MainActivity.class);
            startActivity(intent);
        });

        // Insets (pour le design)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.connexion), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}