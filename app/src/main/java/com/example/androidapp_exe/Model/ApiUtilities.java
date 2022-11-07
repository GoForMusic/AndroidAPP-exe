package com.example.androidapp_exe.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static String APIkey = "d1a54a30d72c48a8a652112783256140";
    private static String BASE_URL = "https://newsapi.org/v2/";
    private static ApiInterface apiInterface;

    public static ApiInterface getApiInterface(){

        if (apiInterface == null){
            apiInterface = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface.class);
        }
        return apiInterface;

    }

    public static String getAPIKey()
    {
        return APIkey;
    }
}
