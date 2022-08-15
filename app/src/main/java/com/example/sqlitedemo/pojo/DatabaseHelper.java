package com.example.sqlitedemo.pojo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "customer.db";
    private static final String CUSTOMER_TABLE = "CUSTOMER_TABLE",
                     COLUMN_CUSTOMER_ID = "ID",
                     COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME",
                     COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE",
                     COLUMN_CUSTOMER_isACTIVE = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, "
                + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_CUSTOMER_isACTIVE + " BOOLEAN)";
        db.execSQL(createDatabaseStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addRecord(CustomerModel customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customer.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customer.getAge());
        cv.put(COLUMN_CUSTOMER_isACTIVE, customer.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        return insert != -1;
    }

    public List<CustomerModel> getAllRecords(){
        List<CustomerModel> allRecords = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllQuery = "SELECT * FROM " + CUSTOMER_TABLE;
        Cursor cursor = db.rawQuery(selectAllQuery, null);
        if (cursor.moveToFirst()){
            do {
                int cursorID = cursor.getInt(0);
                String cursorName = cursor.getString(1);
                int cursorAge = cursor.getInt(2);
                boolean cursorIsActive = cursor.getInt(3) == 1;
                CustomerModel c = new CustomerModel(cursorID,cursorName, cursorAge, cursorIsActive);
                allRecords.add(c);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allRecords;
    }

}
