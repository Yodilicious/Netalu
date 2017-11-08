package com.netalu.netaluapp;

import android.provider.BaseColumns;

/**
 * Created by Jodi on 11/7/2017.
 */

public final class NetaluDatabaseContract {

    private NetaluDatabaseContract() {  }

    public static final class CategoriesEntry implements BaseColumns {

        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";

        // CREATE TABLE categories (record_id, name, description)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT NOT NULL)";
    }

    public static final class FoodGroupsEntry implements BaseColumns {

        public static final String TABLE_NAME = "food_groups";
        public static final String COLUMN_PARENT_ID = "parent_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";

        // CREATE TABLE food_groups (record_id, parent_id, name, description)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_PARENT_ID + " INTEGER NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT NOT NULL)";
    }

    public static final class ReviewsEntry implements BaseColumns {

        public static final String TABLE_NAME = "reviews";
        public static final String COLUMN_FARM_ID = "farm_id";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_REVIEW = "review";

        // CREATE TABLE reviews (record_id, farm_id, rating, review)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_FARM_ID + " INTEGER NOT NULL, " +
                        COLUMN_RATING + " INTEGER NOT NULL, " +
                        COLUMN_REVIEW + " TEXT NOT NULL)";
    }

    public static final class ScheduleHoursEntry implements BaseColumns {

        public static final String TABLE_NAME = "schedule_hours";
        public static final String COLUMN_SCHEDULE_ID = "schedule_id";
        public static final String COLUMN_OPENTIME = "open_time";
        public static final String COLUMN_CLOSETIME = "close_time";

        // CREATE TABLE reviews (record_id, schedule_id, open_time, close_time)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_SCHEDULE_ID + " INTEGER NOT NULL, " +
                        COLUMN_OPENTIME + " TEXT NOT NULL, " +
                        COLUMN_CLOSETIME + " TEXT NOT NULL)";
    }

    public static final class ScheduleEntry implements BaseColumns {

        public static final String TABLE_NAME = "schedule";
        public static final String COLUMN_FARM_ID = "farm_id";
        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_ISOPEN = "is_open";

        // CREATE TABLE reviews (record_id, farm_id, day, is_open)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_FARM_ID + " INTEGER NOT NULL, " +
                        COLUMN_DAY + " TEXT NOT NULL, " +
                        COLUMN_ISOPEN + " TEXT NOT NULL)";
    }

    public static final class FarmEntry implements BaseColumns {

        public static final String TABLE_NAME = "farm";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_FOODGROUP_ID = "foodgroup_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ADDRESS1 = "address1";
        public static final String COLUMN_ADDRESS2 = "address2";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_PROVINCE = "province";
        public static final String COLUMN_POSTALCODE = "postal_code";
        public static final String COLUMN_PHONENUMBER = "phone_number";
        public static final String COLUMN_WEBSITE = "website";

        // CREATE TABLE reviews (record_id, category_id, foodgroup_id, name, description, address1, address2, city, province, postal_code, phone_number, website)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_CATEGORY_ID + " INTEGER NOT NULL, " +
                        COLUMN_FOODGROUP_ID + " INTEGER NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                        COLUMN_ADDRESS1 + " TEXT NOT NULL, " +
                        COLUMN_ADDRESS2 + " TEXT NOT NULL, " +
                        COLUMN_CITY + " TEXT NOT NULL, " +
                        COLUMN_PROVINCE + " TEXT NOT NULL, " +
                        COLUMN_POSTALCODE + " TEXT NOT NULL, " +
                        COLUMN_PHONENUMBER + " TEXT NOT NULL, " +
                        COLUMN_WEBSITE + " TEXT NOT NULL)";
    }
}
