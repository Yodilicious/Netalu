package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.Schedule;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSchedule(Schedule schedule);

    @Query("SELECT * FROM schedule")
    public List<Schedule> getAllSchedules();

    @Query("SELECT * FROM schedule WHERE id = :schedule_id")
    public List<Schedule> getSchedule(int schedule_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSchedule(Schedule schedule);

    @Query("DELETE FROM schedule WHERE id = :schedule_id")
    void deleteSchedule(int schedule_id);

    @Query("DELETE FROM schedule")
    void deleteAllSchedules();
}
