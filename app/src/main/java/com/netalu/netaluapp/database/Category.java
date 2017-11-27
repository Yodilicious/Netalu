package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey
    public final int id;
    public String name;
    public String decription;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.decription = description;
    }
}