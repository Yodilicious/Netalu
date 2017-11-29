package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Query("SELECT * FROM user")
    public List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id = :user_id")
    public List<User> getUser(int user_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Query("DELETE FROM user WHERE id = :user_id")
    void removeUser(int user_id);

    @Query("DELETE FROM user")
    void deleteAllUsers();
}
