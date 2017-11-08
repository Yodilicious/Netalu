package com.netalu.netaluapp;

/**
 * Created by Jodi on 11/7/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NetaluOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Netalu.db";
    public static final int DATABASE_VERSION = 1;

    public NetaluOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NetaluDatabaseContract.CategoriesEntry.SQL_CREATE_TABLE);
        db.execSQL(NetaluDatabaseContract.FoodGroupsEntry.SQL_CREATE_TABLE);
        db.execSQL(NetaluDatabaseContract.ReviewsEntry.SQL_CREATE_TABLE);
        db.execSQL(NetaluDatabaseContract.ScheduleHoursEntry.SQL_CREATE_TABLE);
        db.execSQL(NetaluDatabaseContract.ScheduleEntry.SQL_CREATE_TABLE);
        db.execSQL(NetaluDatabaseContract.FarmEntry.SQL_CREATE_TABLE);

        //DatabaseDataWorker worker = new DatabaseDataWorker(db);
        //worker.insertCourses();
        //worker.insertSampleNotes();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
