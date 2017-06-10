package com.jssaten.sqlitesample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.jssaten.sqlitesample.bean.Student;

import java.util.ArrayList;

/**
 * Created by ashwani on 8/6/17.
 */

public class DataProvider {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "STUDENT";
    public static final String COLUMN_ENROLL = "enrollment";
    public static final String COLUMN_NAME = "studentName";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_GRADUATION = "graduation";
    public static final String COLUMN_SECTION = "section";

    public static final String TAG = DataProvider.class.getSimpleName();

    private DBHelper dbHelper;

    public DataProvider(Context context){
        dbHelper = new DBHelper(context);
    }

    public boolean insertData(Student student){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, student.getName());
        contentValues.put(COLUMN_ENROLL, student.getEnrollment());
        contentValues.put(COLUMN_SECTION, student.getSection());
        contentValues.put(COLUMN_MARKS, student.getMarks());
        contentValues.put(COLUMN_GRADUATION, student.getGraduation());
        db.insert(TABLE_NAME, null, contentValues);

        Log.v(TAG, "DATA INSERTION THROUGH OBJECT COMPLETED!!");
        return true;
    }

    public boolean insertData (String name, int enroll, String section, float marks,String graduation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ENROLL, enroll);
        contentValues.put(COLUMN_SECTION, section);
        contentValues.put(COLUMN_MARKS, marks);
        contentValues.put(COLUMN_GRADUATION, graduation);
        db.insert(TABLE_NAME, null, contentValues);

        Log.v(TAG, "DATA NORMAL INSERTION COMPLETED!!");
        return true;
    }

    public Cursor getData(int enrollment) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_ENROLL+" = "+enrollment+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (String name, int enroll, String section, float marks,String graduation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ENROLL, enroll);
        contentValues.put(COLUMN_SECTION, section);
        contentValues.put(COLUMN_MARKS, marks);
        contentValues.put(COLUMN_GRADUATION, graduation);
        db.update(TABLE_NAME, contentValues, ""+COLUMN_ENROLL+" = ? ", new String[] { Integer.toString(enroll) } );
        return true;
    }

    public Integer deleteContact (Integer enroll) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(TABLE_NAME,
                ""+COLUMN_ENROLL+" = ? ",
                new String[] { Integer.toString(enroll) });
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Student student = new Student();
            student.setName(res.getString(res.getColumnIndex(COLUMN_NAME)));
            student.setGraduation(res.getString(res.getColumnIndex(COLUMN_GRADUATION)));
            student.setMarks(res.getFloat(res.getColumnIndex(COLUMN_MARKS)));
            student.setEnrollment(res.getInt(res.getColumnIndex(COLUMN_ENROLL)));
            student.setSection(res.getString(res.getColumnIndex(COLUMN_SECTION)));
            studentList.add(student);
            res.moveToNext();
        }
        return studentList;
    }

}
