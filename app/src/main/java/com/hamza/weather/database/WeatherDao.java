package com.hamza.weather.database;


import androidx.room.Dao;
import androidx.room.Insert;
import com.hamza.weather.model.Main;


@Dao
public interface WeatherDao {

    @Insert
    void insertMainData(Main mainData);
}
