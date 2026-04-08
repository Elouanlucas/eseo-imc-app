package com.example.icm_calculator.utilities;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper{   // Infos générales de la BDD
    private static final String DATABASE_NAME    = "imc_database.db";
    private static final int    DATABASE_VERSION = 1;

    // Table User
    public static final String TABLE_USER        = "User";
    public static final String COL_USER_NAME     = "username";
    public static final String COL_ID            = "_id";
    public static final String COL_HEIGHT        = "height";
    public static final String COL_WEIGHT        = "weight";
    public static final String COL_GENDER        = "gender";
    public static final String COL_BIRTHDATE     = "birthdate";
    public static final String COL_UNIT          = "unit";

    // Table Historique IMC
    public static final String TABLE_HISTORY     = "IMCHistory";
    public static final String COL_HIST_ID       = "_id";
    public static final String COL_HIST_IMC      = "imc_value";
    public static final String COL_HIST_DATE     = "date";
    public static final String COL_HIST_LABEL    = "interpretation";

    // Requêtes de création des tables
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_USER + " (" +
                    COL_USER_NAME + " TEXT NOT NULL, " +
                    COL_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HEIGHT    + " REAL NOT NULL, " +
                    COL_WEIGHT    + " REAL NOT NULL, " +
                    COL_GENDER    + " TEXT, " +
                    COL_BIRTHDATE + " TEXT, " +
                    COL_UNIT      + " TEXT DEFAULT 'cm');";

    private static final String CREATE_TABLE_HISTORY =
            "CREATE TABLE " + TABLE_HISTORY + " (" +
                    COL_HIST_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_HIST_IMC   + " REAL NOT NULL, " +
                    COL_HIST_DATE  + " TEXT NOT NULL, " +
                    COL_HIST_LABEL + " TEXT);";

    // Constructeur
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Appelée automatiquement à la première ouverture de la BDD
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_HISTORY);
    }

    // Appelée si DATABASE_VERSION augmente
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}
