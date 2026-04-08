package com.example.icm_calculator.utilities;

import android.content.ContentValues;
import android.database.Cursor;
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
    public void updateData(Table table, Column column, Object value) {
        if (db == null) return;

        ContentValues values = new ContentValues();

        if (value instanceof String) {
            values.put(column.getColumn(), (String) value);
        } else if (value instanceof Double) {
            values.put(column.getColumn(), (Double) value);
        } else if (value instanceof Integer) {
            values.put(column.getColumn(), (Integer) value);
        }

        if (values.size() == 0) return;

        db.update(
                table.getName(),                      // table
                values,                               // valeurs
                DBHelper.COL_ID + " = ?",            // WHERE
                new String[]{"1"}                    // args
        );
    }
    public Cursor getDataByColumn(Table table, String column, Object value) {
        if (db == null) return null;

        return db.query(
                table.getName(),        // table
                null,                   // toutes les colonnes
                column + " = ?",        // WHERE
                new String[]{value.toString()},    // valeur
                null,
                null,
                null
        );
    }

    public void insertUser(String name, double height, double weight) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_USER_NAME, name);
        values.put(DBHelper.COL_HEIGHT, height);
        values.put(DBHelper.COL_WEIGHT, weight);

        db.insert(DBHelper.TABLE_USER, null, values);
    }
}
