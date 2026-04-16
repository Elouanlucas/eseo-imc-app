package com.example.icm_calculator.utilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO {

    private SQLiteDatabase db;

    public DAO(DBHelper dbHelper) {
        this.db = dbHelper.getWritableDatabase();
    }

    // -------------------------
    // INSERT USER
    // -------------------------

    public long insertUser(String name, double height, double weight,
                           String gender, String birthdate, String unit) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_USER_NAME, name);
        values.put(DBHelper.COL_HEIGHT, height);
        values.put(DBHelper.COL_WEIGHT, weight);
        values.put(DBHelper.COL_GENDER, gender);
        values.put(DBHelper.COL_BIRTHDATE, birthdate);
        values.put(DBHelper.COL_UNIT, unit);

        return db.insert(DBHelper.TABLE_USER, null, values);
    }

    // -------------------------
    // GET USER BY USERNAME
    // -------------------------

    public Cursor getUserByUsername(String username) {
        return db.query(
                DBHelper.TABLE_USER,
                null,
                DBHelper.COL_USER_NAME + " = ?",
                new String[]{username},
                null,
                null,
                null
        );
    }

    // -------------------------
    // UPDATE USER
    // -------------------------

    public void updateUser(int id, String name, double height, double weight,
                           String gender, String birthdate, String unit) {

        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_USER_NAME, name);
        values.put(DBHelper.COL_HEIGHT, height);
        values.put(DBHelper.COL_WEIGHT, weight);
        values.put(DBHelper.COL_GENDER, gender);
        values.put(DBHelper.COL_BIRTHDATE, birthdate);
        values.put(DBHelper.COL_UNIT, unit);

        db.update(
                DBHelper.TABLE_USER,
                values,
                DBHelper.COL_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }
}