package com.jssaten.sqlitesample.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ashwani on 8/6/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "STUDENT";
    public static final String COLUMN_ENROLL = "enrollment";
    public static final String COLUMN_NAME = "studentName";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_GRADUATION = "graduation";
    public static final String COLUMN_SECTION = "section";

    public static int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TABLE_NAME +
                        " ( "+COLUMN_ENROLL+" integer primary key, "+COLUMN_NAME+" text," +
                        COLUMN_GRADUATION + " text,"
                        +COLUMN_MARKS+" REAL, "+
                        COLUMN_SECTION+" text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
