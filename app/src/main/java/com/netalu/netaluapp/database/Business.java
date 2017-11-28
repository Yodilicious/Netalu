package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "business",
    foreignKeys = {
        @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = ForeignKey.NO_ACTION
        ),
        @ForeignKey(
                entity = FoodGroup.class,
                parentColumns = "id",
                childColumns = "foodgroup_id",
                onDelete = ForeignKey.NO_ACTION
        )
    },
    indices = { @Index(value = "id") }
)
public class Business {
    @PrimaryKey
    public final int id;
    public int category_id;
    public int foodgroup_id;
    public String name;
    public String description;
    public String address1;
    public String address2;
    public String city;
    public String province;
    public String postal_code;
    public String phone_number;
    public String website;

    public Business(int id, int category_id, int foodgroup_id, String name, String description, String address1, String address2,
                String city, String province, String postal_code, String phone_number, String website) {
        this.id = id;
        this.category_id = category_id;
        this.foodgroup_id = foodgroup_id;
        this.name = name;
        this.description = description;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.website = website;
    }
}
