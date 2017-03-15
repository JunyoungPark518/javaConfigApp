package com.hanbit.javaconfigapp.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hb2005 on 2017-03-15.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    final static String DATABASE_NAME = "messenger.db";
    final static Integer DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Message (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "writeDate TEXT," +
                "isReadable TEXT," +
                "sender TEXT," +
                "receiver TEXT," +
                "temp2 TEXT," +
                "temp3 TEXT," +
                "temp4 TEXT," +
                "temp5 TEXT," +
                "temp6 TEXT," +
                "temp7 TEXT," +
                "temp8 TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Message");
        onCreate(db);
    }
}
