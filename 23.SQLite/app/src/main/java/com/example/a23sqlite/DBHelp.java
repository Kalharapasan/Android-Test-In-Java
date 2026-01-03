package com.example.a23sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp extends SQLiteOpenHelper {

    public static final String dbName = "User.db";

    public DBHelp(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fristName TEXT, " +
                "lastName TEXT, " +
                "gender TEXT, " +
                "mail TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users", null);
    }

    public Cursor getDataById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE ID = ?", new String[]{id});
    }

    public long inserData(String fName, String lName, String gender, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fristName", fName);
        contentValues.put("lastName", lName);
        contentValues.put("gender", gender);
        contentValues.put("mail", email);
        return db.insert("users", null, contentValues);
    }

    public boolean updateData(String id, String fName, String lName, String gender, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fristName", fName);
        contentValues.put("lastName", lName);
        contentValues.put("gender", gender);
        contentValues.put("mail", email);
        int result = db.update("users", contentValues, "ID = ?", new String[]{id});
        return result > 0;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("users", "ID = ?", new String[]{id});
        return result > 0;
    }
}
