package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void AddReview(Review review);

    @Query("SELECT * FROM review")
    public List<Review> getAllReviews();

    @Query("SELECT * FROM review WHERE business_id = :business_id")
    public List<Review> getAllReviewsForBusiness(int business_id);

    @Query("SELECT * FROM review WHERE id = :review_id")
    public List<Review> getReview(int review_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateReview(Review review);

    @Query("DELETE FROM review WHERE id = :review_id")
    void deleteReview(int review_id);

    @Query("DELETE FROM review")
    void deleteAllReviews();
}
