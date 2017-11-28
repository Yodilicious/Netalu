package com.netalu.netaluapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.netalu.netaluapp.database.ScheduleHour;

import java.util.List;

@Dao
public interface ScheduleHourDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addScheduleHour(ScheduleHour scheduleHour);

    @Query("SELECT * FROM schedule_hour")
    public List<ScheduleHour> getAllScheduleHours();

    @Query("SELECT * FROM schedule_hour WHERE id = :schedule_hour_id")
    public List<ScheduleHour> getScheduleHour(int schedule_hour_id);

    @Query("SELECT * FROM schedule_hour WHERE schedule_id = :schedule_id")
    public List<ScheduleHour> getScheduleHoursForSchedule(int schedule_id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateScheduleHour(ScheduleHour scheduleHour);

    @Query("DELETE FROM schedule_hour WHERE id = :schedule_hour_id")
    void deleteScheduleHour(int schedule_hour_id);

    @Query("DELETE FROM schedule_hour")
    void deleteAllScheduleHours();
}
