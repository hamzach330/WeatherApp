package com.hamza.weather.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hamza.weather.model.Main;
import com.hamza.weather.model.WeatherData;

@Database(entities = {Main.class,
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao getDAO();


}