package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.BusinessFoodGroup;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

@Dao
public interface BusinessFoodGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBusinessFoodGroup(FoodGroup foodGroup);

    @Query("SELECT * FROM business_food_group")
    public List<BusinessFoodGroup> getAllBusinessFoodGroups();

    @Query("SELECT * FROM business_food_group WHERE id = :food_group_id")
    public List<FoodGroup> getBusinessFoodGroup(int food_group_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBusinessFoodGroup(BusinessFoodGroup businessFoodGroup);

    @Query("DELETE FROM business_food_group WHERE id = :business_food_group_id")
    void removeBusinessFoodGroup(int business_food_group_id);

    @Query("DELETE FROM business_food_group")
    void deleteAllBusinessFoodGroups();
}
