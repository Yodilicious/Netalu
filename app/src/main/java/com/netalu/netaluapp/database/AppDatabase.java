package com.netalu.netaluapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.netalu.netaluapp.dao.BusinessDao;
import com.netalu.netaluapp.dao.CategoryDao;
import com.netalu.netaluapp.dao.FoodGroupDao;
import com.netalu.netaluapp.dao.ReviewDao;
import com.netalu.netaluapp.dao.ScheduleDao;
import com.netalu.netaluapp.dao.ScheduleHourDao;

@Database(entities =
    {
        Business.class,
        Category.class,
        FoodGroup.class,
        Review.class,
        Schedule.class,
        ScheduleHour.class
    }, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract BusinessDao businessDao();
    public abstract CategoryDao categoryDao();
    public abstract FoodGroupDao foodGroupDao();
    public abstract ReviewDao reviewDao();
    public abstract ScheduleDao scheduleDao();
    public abstract ScheduleHourDao scheduleHourDao();

    public static AppDatabase getDatabase(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "netaludb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}