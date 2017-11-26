package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ScheduleHour {
    @PrimaryKey
    public final int id;
    public int schedule_id;
    public String open_time;
    public String close_time;

    public ScheduleHour(int id, int schedule_id, String open_time, String close_time) {
        this.id = id;
        this.schedule_id = schedule_id;
        this.open_time = open_time;
        this.close_time = close_time;
    }
}
