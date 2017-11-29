package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "business_food_group",
    foreignKeys = {
        @ForeignKey(
            entity = Business.class,
            parentColumns = "id",
            childColumns = "business_id",
            onDelete = ForeignKey.NO_ACTION
        ),
        @ForeignKey(
            entity = FoodGroup.class,
            parentColumns = "id",
            childColumns = "food_group_id",
            onDelete = ForeignKey.NO_ACTION
        )
},
        indices = { @Index(value = "id") }
)
public class BusinessFoodGroup {

    @PrimaryKey(autoGenerate = true)
    public final int id;
    public int business_id;
    public int food_group_id;

    public BusinessFoodGroup(int id, int business_id, int food_group_id) {
        this.id = id;
        this.business_id = business_id;
        this.food_group_id = food_group_id;
    }
}
