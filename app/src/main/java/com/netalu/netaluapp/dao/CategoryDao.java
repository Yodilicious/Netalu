package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(Category category);

    @Query("SELECT * FROM category")
    public List<Category> getAllCategories();

    @Query("SELECT * FROM category WHERE id = :category_id")
    public List<Category> getCategory(int category_id);

    @Query("SELECT * FROM category WHERE food_group_id = :food_group_id")
    public List<Category> getAllCatetoriesForFoodGroup(int food_gorup_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Category category);

    @Query("DELETE FROM category WHERE id = :category_id")
    void removeCategory(int category_id);

    @Query("DELETE FROM category")
    void removeAllCategories();
}
