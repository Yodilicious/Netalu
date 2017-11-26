package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Review {
    @PrimaryKey
    public final int id;
    public int farm_id;
    public int rating;
    public String review;

    public Review(int id, int farm_id, int rating, String review) {
        this.id = id;
        this.farm_id = farm_id;
        this.rating = rating;
        this.review = review;
    }
}
