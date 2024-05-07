package com.example.nestquest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "service_provider.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + ServiceProviderContract.ServiceProviderEntry.TABLE_NAME + " (" +
                ServiceProviderContract.ServiceProviderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ServiceProviderContract.ServiceProviderEntry.COLUMN_FULL_NAME + " TEXT, " +
                ServiceProviderContract.ServiceProviderEntry.COLUMN_CONTACT_NUMBER + " TEXT, " +
                ServiceProviderContract.ServiceProviderEntry.COLUMN_CHARGE + " REAL, " +
                ServiceProviderContract.ServiceProviderEntry.COLUMN_SERVICE_PROVIDED + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ServiceProviderContract.ServiceProviderEntry.TABLE_NAME);
        onCreate(db);
    }

    // Insert method using ServiceProfile object
    public long insertServiceProvider(String fullName, String contactNumber, double charge, String serviceProvided) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_FULL_NAME, fullName);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_CHARGE, charge);
        values.put(ServiceProviderContract.ServiceProviderEntry.COLUMN_SERVICE_PROVIDED, serviceProvided);
        long id = db.insert(ServiceProviderContract.ServiceProviderEntry.TABLE_NAME, null, values);
        db.close();
        return id;
    }
}

