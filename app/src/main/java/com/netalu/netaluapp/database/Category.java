package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "category",
    foreignKeys = {
        @ForeignKey(
            entity = FoodGroup.class,
            parentColumns = "id",
            childColumns = "food_group_id",
            onDelete = ForeignKey.NO_ACTION
        )
    },
    indices = { @Index(value = "id") }
)
public class Category {

    @PrimaryKey
    public final int id;
    public int food_group_id;
    public String name;
    public String decription;

    public Category(int id, int food_group_id, String name, String description) {
        this.id = id;
        this.food_group_id = food_group_id;
        this.name = name;
        this.decription = description;
    }
}