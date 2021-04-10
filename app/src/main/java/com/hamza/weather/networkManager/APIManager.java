package com.hamza.weather.networkManager;


import android.util.Log;

import androidx.annotation.NonNull;
import com.hamza.weather.BuildConfig;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static final String TAG = "APIManager";
    private static final APIManager instance = new APIManager();
    private Retrofit retrofit;
    private String AppId;


    public interface Callback {
        void onSuccess(int code, ApiResponse response);

        void onFailure(int code, String error);
    }

    public static APIManager getInstance() {
        return instance;
    }

    private APIManager() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        AppId = BuildConfig.AppID;
    }


    public void getWeather(final Callback callback, String cityname) {
        APIInterface service = retrofit.create(APIInterface.class);
        Call<ApiResponse> result = service.getWeather(cityname, AppId);
        sendRequest(callback, result, false);
    }


    private void sendRequest(final Callback callback, Call<ApiResponse> result, Boolean flag) {
        result.clone().enqueue(new retrofit2.Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull retrofit2.Response<ApiResponse> response) {
                if (response.body() != null) {
                    try {
                        if (callback != null) {
                            if (response.code() == 200) {
                                callback.onSuccess(response.body().getRespCode(), response.body());
                            } else {
                                callback.onFailure(response.code(), response.errorBody().string());
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(response.code(), response.message());
                    }
                    Log.e("Error", "" + response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.fillInStackTrace();
                if (callback != null) {
                    String message;
                    if (t instanceof SocketTimeoutException || t instanceof ConnectException) {
                        message = "Fail to Connect to Server\nPlease try again later.";
                        callback.onFailure(-1, message);
                    } else {
                        callback.onFailure(-1, t.getMessage());
                    }

                }
            }
        });
    }





}
