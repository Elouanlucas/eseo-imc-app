package com.example.icm_calculator;

import com.example.icm_calculator.utilities.*;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculeIMCActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private DAO dao;

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

        dbHelper = new DBHelper(this);
        dao = new DAO(dbHelper);
        //user default values
        dao.insertUser("Jhon", 180, 75);
    }
    public void onClick(View view){
        if (view.getId() == R.id.calculate_button){
            Log.d("IMC_DEBUG","Button clicked");
            //enregistrements des élements dans la bdd

            EditText height = findViewById(R.id.height_editable);
            dao.updateData(DAO.Table.USER, DAO.Column.HEIGHT, height.getText().toString());
        }
    }

    //boutton de test example
    public void testOnClick(View view){
        Log.d("IMC_DEBUG","Button clicked");
        Cursor val = dao.getDataByColumn(
                DAO.Table.USER,
                DBHelper.COL_ID,
                "1"
        );
        if (val != null && val.moveToFirst()) {
            double height = val.getDouble(
                    val.getColumnIndexOrThrow(DBHelper.COL_HEIGHT)
            );
            String username = val.getString(
                    val.getColumnIndexOrThrow(DBHelper.COL_USER_NAME)
            );
            Log.d("IMC_DEBUG", "User: " + username + " Height: " + height);
            val.close();
        } else {
            Log.d("IMC_DEBUG", "Aucune donnée trouvée");
        }
    }
}
