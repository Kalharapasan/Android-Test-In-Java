package com.example.a21sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StudentDB.db";

    // Table Name
    private static final String TABLE_STUDENTS = "students";

    // Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_AGE = "age";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_AGE + " INTEGER" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    // Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        // Create tables again
        onCreate(db);
    }

    // ==================== CRUD OPERATIONS ====================

    // CREATE - Add new student
    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_AGE, student.getAge());

        // Inserting Row
        long id = db.insert(TABLE_STUDENTS, null, values);
        db.close();

        return id;
    }

    // READ - Get single student by ID
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS,
                new String[]{KEY_ID, KEY_NAME, KEY_EMAIL, KEY_AGE},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(
                cursor.getInt(0),    // id
                cursor.getString(1), // name
                cursor.getString(2), // email
                cursor.getInt(3));   // age

        cursor.close();
        db.close();

        return student;
    }

    // READ - Get all students
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS + " ORDER BY " + KEY_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getInt(0),    // id
                        cursor.getString(1), // name
                        cursor.getString(2), // email
                        cursor.getInt(3));   // age

                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentList;
    }

    // UPDATE - Update student details
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_AGE, student.getAge());

        // Updating row
        int result = db.update(TABLE_STUDENTS, values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});

        db.close();
        return result;
    }

    // DELETE - Delete single student
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STUDENTS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});

        db.close();
    }

    // DELETE - Delete all students
    public void deleteAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STUDENTS);
        db.close();
    }

    // Get total student count
    public int getStudentCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }
}