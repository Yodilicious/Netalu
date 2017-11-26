package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Schedule {
    @PrimaryKey
    public final int id;
    public int farm_id;
    public String day;
    public String is_open;

    public Schedule(int id, int farm_id, String day, String is_open) {
        this.id = id;
        this.farm_id = farm_id;
        this.day = day;
        this.is_open = is_open;
    }
}
