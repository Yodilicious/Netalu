package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

@Dao
public interface FoodGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFoodGroup(FoodGroup foodGroup);

    @Query("SELECT * FROM food_group")
    public List<FoodGroup> getAllFoodGroups();

    @Query("SELECT * FROM food_group WHERE id = :food_group_id")
    public List<FoodGroup> getFoodGroup(int food_group_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFoodGroup(FoodGroup foodGroup);

    @Query("DELETE FROM food_group WHERE id = :food_group_id")
    void removeFoodGroup(int food_group_id);

    @Query("DELETE FROM food_group")
    void removeAllFoodGroups();
}
