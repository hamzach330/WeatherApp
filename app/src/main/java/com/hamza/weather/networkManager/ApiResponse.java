package com.hamza.weather.networkManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hamza.weather.model.WeatherData;

import java.util.ArrayList;


public class ApiResponse {

    @SerializedName("cod")
    @Expose
    private Integer respCode;

    @SerializedName("message")
    @Expose
    private int respMessage;

    @SerializedName("list")
    @Expose
    private ArrayList<WeatherData> data;

    public ArrayList<WeatherData> getData() {
        return data;
    }

    public void setData(ArrayList<WeatherData> data) {
        this.data = data;
    }

    public Integer getRespCode() {
        return respCode;
    }
    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public int getRespMessage() {
        return respMessage;
    }
    public void setRespMessage(int respMessage) {
        this.respMessage = respMessage;
    }
}