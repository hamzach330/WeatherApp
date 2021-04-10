package com.hamza.weather.networkManager;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    @GET("forecast")
    Call<ApiResponse> getWeather(@Query("q") String cityname,@Query("appid") String appid);

}