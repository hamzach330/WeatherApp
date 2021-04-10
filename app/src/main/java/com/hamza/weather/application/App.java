package com.hamza.weather.application;

import android.app.Application;


import com.hamza.weather.networkManager.Urls;


/**
 * Created by Hamza Masood on 18,July,2019
 */


public class App extends Application {

    private static App INSTANCE = null;
    public static synchronized App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Urls.SetURL(Urls.Environment.Live_PWR);
    }


}
