package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FoodGroup {
    @PrimaryKey
    public final int id;
    public int parent_id;
    public String name;
    public String description;

    public FoodGroup(int id, int parent_id, String name, String description) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.description = description;
    }
}
