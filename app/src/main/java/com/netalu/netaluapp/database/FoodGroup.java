package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "food_group",
    indices = { @Index(value = "id") }
)
public class FoodGroup {

    @PrimaryKey(autoGenerate = true)
    public final int id;
    public String name;
    public String description;

    public FoodGroup(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
