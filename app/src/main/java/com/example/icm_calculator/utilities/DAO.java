package com.example.icm_calculator.utilities;

import android.database.sqlite.SQLiteDatabase;


public class DAO {
    private SQLiteDatabase db = null;
    public DAO(DBHelper dbHelper) {
        db = dbHelper.getWritableDatabase();
    }

    public enum Table {
        USER("User"),
        HISTORY("IMCHistory");
        private final String tableName;
        Table(String tableName) {
            this.tableName = tableName;
        }
        public String getName() {
            return tableName;
        }
    }

    public enum Column{
        USER_NAME("username"),
        HEIGHT("height"),
        WEIGHT("weight"),
        GENDER("gender"),
        BIRTH_DATE("birthdate"),
        UNIT("unit");
        public final String column;
        Column(String column){
            this.column = column;
        }

        public String getColumn(){
            return column;
        }

    }

    // save all value of user
    public void updateData(Column colomn, Table table, Object value){

        try{
            String query = "UPDATE TABLE" + table + "SET"+ colomn + "=" + value;
            if (db != null ) {
                db.execSQL(query);
            }else{
                System.out.println("Erreur lors de l'ouverture de la BDD");
                throw new Error("Erreur lors de l'ouverture de la BDD");
            }
        } catch (Error err){
            System.out.println("ERROR lors de la mise à jour des données \n code d'erreur :"+ err.getMessage());
            throw err;
        }
    }
}
