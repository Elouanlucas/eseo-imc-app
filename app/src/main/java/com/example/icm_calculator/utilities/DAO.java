package com.example.icm_calculator.utilities;

import android.content.ContentValues;
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
    public void updateUserData(Column column, Object value) {
        if (db == null) return;

        ContentValues values = new ContentValues();

        if (value instanceof String) {
            values.put(column.getColumn(), (String) value);
        } else if (value instanceof Double) {
            values.put(column.getColumn(), (Double) value);
        } else if (value instanceof Integer) {
            values.put(column.getColumn(), (Integer) value);
        }

        // On met à jour l'utilisateur avec ID = 1
        db.update(DBHelper.TABLE_USER, values, DBHelper.COL_ID + " = ?", new String[]{"1"});
    }
}
