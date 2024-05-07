package com.example.nestquest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserProfile.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NAME = "user_profiles";

    // Table columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_AADHAR = "aadhar";
    public static final String COLUMN_CONTACT_TIME = "contact_time";
    public static final String COLUMN_RENT = "rent";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_NUMBER_OF_ROOMS = "number_of_rooms";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_ATTACHED_BATHROOM = "attached_bathroom";
    public static final String COLUMN_PARKING_FACILITY = "parking_facility";
    public static final String COLUMN_WIFI_FACILITY = "wifi_facility";
    public static final String COLUMN_STOREY = "storey";
    public static final String COLUMN_HOUSING_TYPE = "housing_type";

    // SQL statement to create the table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_FULL_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_PHONE + " TEXT," +
                    COLUMN_AADHAR + " TEXT," +
                    COLUMN_CONTACT_TIME + " TEXT," +
                    COLUMN_RENT + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_NUMBER_OF_ROOMS + " TEXT," +
                    COLUMN_AREA + " TEXT," +
                    COLUMN_ATTACHED_BATHROOM + " INTEGER," +
                    COLUMN_PARKING_FACILITY + " INTEGER," +
                    COLUMN_WIFI_FACILITY + " INTEGER," +
                    COLUMN_STOREY + " TEXT," +
                    COLUMN_HOUSING_TYPE + " TEXT" +
                    ");";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
