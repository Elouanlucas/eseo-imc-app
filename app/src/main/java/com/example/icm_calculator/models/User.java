package com.example.icm_calculator.models;

import android.content.Context;
import android.database.Cursor;

import com.example.icm_calculator.utilities.DAO;
import com.example.icm_calculator.utilities.DBHelper;

public class User {

    private int id;
    private String username;
    private double height;
    private double weight;
    private String gender;
    private String birthdate;
    private String unit;

    private DAO dao;

    public User(Context context) {
        if (context != null) {
            this.dao = new DAO(new DBHelper(context));
        }
    }

    // -------------------------
    // GETTERS / SETTERS
    // -------------------------

    public int getId() { return id; }
    public String getUsername() { return username; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getGender() { return gender; }
    public String getBirthdate() { return birthdate; }
    public String getUnit() { return unit; }

    public void setUsername(String username) { this.username = username; }
    public void setHeight(double height) { this.height = height; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public void setUnit(String unit) { this.unit = unit; }

    // -------------------------
    // REGISTER
    // -------------------------

    public void register() {
        if (dao == null) return;

        long newId = dao.insertUser(username, height, weight, gender, birthdate, unit);
        this.id = (int) newId;
    }

    // -------------------------
    // LOGIN
    // -------------------------

    public boolean login(String username) {
        if (dao == null) return false;

        Cursor cursor = dao.getUserByUsername(username);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COL_ID));
            this.username = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_USER_NAME));
            this.height = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COL_HEIGHT));
            this.weight = cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COL_WEIGHT));
            this.gender = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_GENDER));
            this.birthdate = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_BIRTHDATE));
            this.unit = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_UNIT));

            cursor.close();
            return true;
        }

        return false;
    }

    // -------------------------
    // UPDATE
    // -------------------------

    public void update() {
        if (dao == null || id == 0) return;

        dao.updateUser(id, username, height, weight, gender, birthdate, unit);
    }

    // -------------------------
    // BMI
    // -------------------------

    public double calculateBMI() {
        double heightMeters = height / 100.0;
        return weight / (heightMeters * heightMeters);
    }

    public String getBMIInterpretation() {
        double bmi = calculateBMI();

        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
}