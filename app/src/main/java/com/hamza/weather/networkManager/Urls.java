package com.hamza.weather.networkManager;

import com.hamza.weather.BuildConfig;

/**
 * Created by Hamza Masood on 22,July,2019
 */

public class Urls {

    public static String BASE_URL = "";
    public static boolean isTestEnv = false;
    /**
     * All Methods/Apis
     */
    private static String refreshToken = "Zuuser/RefreshUserToken";
    private static String GetVerificationCode = "Zuuser/GetVerificationCode";
    private static String GetUser = "Zuuser/GetUser";
    private static String RegisterUser = "Zuuser/Register";
    private static String QRCode = "Zuuser/GetQRCode";
    private static String FareEstimation = "Travel/FareEstimation";
    private static String RechargeHistory = "Wallet/RechargeHistory";
    private static String TravelHistory = "Travel/TravelHistory";
    private static String AddTopup = "Wallet/AddTopup";
    private static String GetAccountBalance = "Wallet/GetAccountBalance";
    private static String GetHTMLStaicContent = "Common/GetStaticContent";
    private static String GetNotifications = "Zuuser/GetUserNotifications";
    private static String GetAdvertImages = "Common/GetAdvtImages";
    private static String GetStops = "ITS/GetStops";
    private static String GetServiceHours = "Travel/ServiceHours";
    private static String GetBusArrivals = "Travel/BusArrivals";
    private static String CreateUserTicket = "Zuuser/CreateUserTicket";


    public static void SetURL(Environment env) {

        switch (env) {
            case Live:  // Also a test URL for now
                BASE_URL = BuildConfig.baseURL;
                isTestEnv = true;
                break;


        }

    }

    public static String url_refreshToken() {
        return BASE_URL + refreshToken;
    }

    public static String url_GetVerificationCode() {
        return BASE_URL + GetVerificationCode;
    }

    public static String url_GetUser() {
        return BASE_URL + GetUser;
    }

    public static String url_RegisterUser() {
        return BASE_URL + RegisterUser;
    }

    public static String url_GetQRCode() {
        return BASE_URL + QRCode;
    }

    public static String url_GetFareEstimation() {
        return BASE_URL + FareEstimation;
    }

    public static String url_GetRechargeHistory() {
        return BASE_URL + RechargeHistory;
    }

    public static String url_GetTravelHistory() {
        return BASE_URL + TravelHistory;
    }

    public static String url_AddTopup() {
        return BASE_URL + AddTopup;
    }

    public static String url_GetAccountBalance() {
        return BASE_URL + GetAccountBalance;
    }

    public static String url_GetHTMLStaicContent() {
        return BASE_URL + GetHTMLStaicContent;
    }

    public static String url_GetStops() {
        return BASE_URL + GetStops;
    }

    public static String url_GetNotifications() {
        return BASE_URL + GetNotifications;
    }

    public static String url_GetAdvertImages() {
        return BASE_URL + GetAdvertImages;
    }

    public static String url_GetServiceHours() {
        return BASE_URL + GetServiceHours;
    }

    public static String url_GetBusArrivals() {
        return BASE_URL + GetBusArrivals;
    }


    public static String url_CreateUserTicket() {
        return BASE_URL + CreateUserTicket;
    }


    /////////
    public enum Environment {Live, Test, Live_PWR}

}
