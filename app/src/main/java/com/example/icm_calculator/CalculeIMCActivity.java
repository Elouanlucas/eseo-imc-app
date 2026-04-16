package com.example.icm_calculator;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.icm_calculator.models.User;

public class CalculeIMCActivity extends AppCompatActivity {

    private static User user; // 👈 user de test (temporaire)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calcule_imc);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText dateEdit = findViewById(R.id.date_edit);

        dateEdit.setOnClickListener(v -> {
            showDatePicker(dateEdit);
        });

        // 👇 INITIALISATION USER TEST
        user = new User(this);

        // 🔥 login (user existe déjà)
        boolean ok = user.login("Jhon");

        if (ok) {
            populateUI(); // 🔥 ici
        } else {
            // création user si besoin
        }

        if (!ok) {
            Log.d("IMC_DEBUG", "User not found, creating...");
            user.setUsername("Jhon");
            user.setHeight(180);
            user.setWeight(75);
            user.setGender("M");
            user.setBirthdate("2000");
            user.setUnit("cm");
            user.register();
        }
    }

    // -------------------------
    // CALCUL IMC
    // -------------------------

    public void onClick(View view) {
        if (view.getId() == R.id.calculate_button) {

            EditText heightInput = findViewById(R.id.height_editable);
            EditText weightInput = findViewById(R.id.weight_editable);
            TextView result = findViewById(R.id.result_text);

            try {
                double height = Double.parseDouble(heightInput.getText().toString());
                double weight = Double.parseDouble(weightInput.getText().toString());

                // 🔥 update user
                user.setHeight(height);
                user.setWeight(weight);
                user.update();

                // 🔥 calcul IMC
                double imc = user.calculateBMI();
                String interpretation = user.getBMIInterpretation();

                result.setText("IMC: " + imc + " (" + interpretation + ")");

                Log.d("IMC_DEBUG", "IMC: " + imc);

            } catch (Exception e) {
                Log.d("IMC_DEBUG", "Erreur input");
            }
        }
    }

    // -------------------------
    // TEST DB
    // -------------------------

    public void testOnClick(View view) {
        Log.d("IMC_DEBUG",
                "User: " + user.getUsername() +
                        " Height: " + user.getHeight() +
                        " Weight: " + user.getWeight()
        );
    }

    private void showDatePicker(EditText dateEdit) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateEdit.setText(date);

                    // 🔥 update user direct
                    user.setBirthdate(date);
                },
                year,
                month,
                day
        );

        datePicker.show();
    }

    private void populateUI() {

        EditText heightInput = findViewById(R.id.height_editable);
        EditText weightInput = findViewById(R.id.weight_editable);
        EditText dateInput = findViewById(R.id.date_edit);
        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        RadioGroup unitGroup = findViewById(R.id.unit_group);

        // 🔥 remplir les champs
        heightInput.setText(String.valueOf(user.getHeight()));
        weightInput.setText(String.valueOf(user.getWeight()));
        dateInput.setText(user.getBirthdate());

        // 🔥 unité
        if ("cm".equals(user.getUnit())) {
            unitGroup.check(R.id.radio_cm);
        } else {
            unitGroup.check(R.id.radio_m);
        }

        // 🔥 gender (spinner)
        String gender = user.getGender();

        if (gender != null) {
            for (int i = 0; i < genderSpinner.getCount(); i++) {
                if (genderSpinner.getItemAtPosition(i).toString().equals(gender)) {
                    genderSpinner.setSelection(i);
                    break;
                }
            }
        }
    }
}