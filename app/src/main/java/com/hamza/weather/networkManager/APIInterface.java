package com.hamza.weather.networkManager;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Tag;

interface APIInterface {


    @GET("forecast")
    Call<ApiResponse> getWeather(@Query("q") String cityname,@Query("appid") String appid);


}