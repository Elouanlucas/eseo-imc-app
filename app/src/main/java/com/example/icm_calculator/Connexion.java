package com.example.icm_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.icm_calculator.log.ConnexionUtilisateur;

public class Connexion extends AppCompatActivity {

    // Déclaration des variables
    ImageButton log;
    Button monBouton;
    EditText editPrenom;
    TextView textRemplire;
    ImageView imageAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connexion);

        // 1. Liaison des vues (on récupère les éléments du XML)
        imageAccueil = findViewById(R.id.imageAccueil);
        monBouton = findViewById(R.id.Calcule);
        editPrenom = findViewById(R.id.editPoids);
        textRemplire = findViewById(R.id.Remplire);
        log = findViewById(R.id.log); // Ton ImageButton en haut à droite

        // 2. Animation au démarrage
        Animation glisserBas = AnimationUtils.loadAnimation(this, R.anim.bas);
        imageAccueil.startAnimation(glisserBas);

        // 3. Gestion des SharedPreferences (Mémoire)
        SharedPreferences sharedPref = getSharedPreferences("MesInfos", MODE_PRIVATE);
        String nomEnregistre = sharedPref.getString("prenom", "");

        if (!nomEnregistre.isEmpty()) {
            textRemplire.setText("Content de vous revoir, " + nomEnregistre);
            editPrenom.setText(nomEnregistre);
        }

        // 4. Écouteur de la touche Entrée pour sauvegarder le prénom
        editPrenom.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == android.view.KeyEvent.ACTION_DOWN) &&
                    (keyCode == android.view.KeyEvent.KEYCODE_ENTER)) {

                String prenomSaisi = editPrenom.getText().toString();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("prenom", prenomSaisi);
                editor.apply();

                textRemplire.setText("Bonjour " + prenomSaisi + " !");
                return true;
            }
            return false;
        });

        // 5. Bouton "Calculer" -> Vers MainActivity
        monBouton.setOnClickListener(v -> {
            Intent intent = new Intent(Connexion.this, MainActivity.class);
            startActivity(intent);
        });

        // 6. Bouton d'icône "Log" -> Vers ConnexionUtilisateur
        // C'est ici que j'ai corrigé : on utilise "log" et non "btnPrisePoids"
        log.setOnClickListener(v -> {
            Intent intent = new Intent(Connexion.this, ConnexionUtilisateur.class);
            startActivity(intent);
        });

        // Gestion des Insets pour le design plein écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.connexion), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}