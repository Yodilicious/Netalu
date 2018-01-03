package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.Business;

import java.util.List;

@Dao
public interface BusinessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBusiness(Business business);

    @Query("SELECT * FROM business")
    public List<Business> getAllBusinesses();

    @Query("SELECT MAX(b.id) FROM business b")
    public int getLastBusinessId();

    @Query("SELECT * FROM business WHERE id = :business_id")
    public List<Business> getBusiness(int business_id);

    @Query("SELECT b.id, b.food_group_id, b.name, b.description, b.address1, b.address2, b.city, b.province, b.postal_code, b.phone_number, b.email, b.website FROM business As b JOIN food_group As fg ON b.food_group_id = fg.id WHERE fg.name = :name")
    public List<Business> getBusinessForFoodGroupName(String name);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBusiness(Business business);

    @Query("DELETE FROM business WHERE id = :business_id")
    void removeBusiness(int business_id);

    @Query("DELETE FROM business")
    void deleteAllBusinesses();
}
