package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "review",
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
public class Review {

    @PrimaryKey(autoGenerate = true)
    public final int id;
    public int business_id;
    public int rating;
    public String review;

    public Review(int id, int business_id, int rating, String review) {
        this.id = id;
        this.business_id = business_id;
        this.rating = rating;
        this.review = review;
    }
}
