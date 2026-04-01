package com.example.icm_calculator.models;

public class User {

    private String username;
    private double height;
    private double weight;
    private String gender;
    private String birthdate;
    private String unit;

    public User(String Username, double height, double weight, String gender, String birthdate, String unit){
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.birthdate = birthdate;
        this.unit = unit;
    }

    // Getters
    public String  getUsername()  { return username; }
    public double  getHeight()    { return height; }
    public double  getWeight()    { return weight; }
    public String getGender()    { return gender; }
    public String getBirthdate() { return birthdate; }
    public String getUnit()      { return unit; }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setHeight(float height) { this.height = height; }
    public void setWeight(float weight) { this.weight = weight; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public void setUnit(String unit) { this.unit = unit; }

}
