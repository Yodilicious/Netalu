package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "schedule",
    foreignKeys = {
        @ForeignKey(
            entity = Business.class,
            parentColumns = "id",
            childColumns = "business_id",
            onDelete = ForeignKey.NO_ACTION
        )
    },
    indices = { @Index(value = "id") }
)
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    public final int id;
    public int business_id;
    public String day;
    public String is_open;

    public Schedule(int id, int business_id, String day, String is_open) {
        this.id = id;
        this.business_id = business_id;
        this.day = day;
        this.is_open = is_open;
    }
}
