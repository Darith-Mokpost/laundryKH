package com.example.laundrykh.Services;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://206.189.82.254/laundry/";
    public static Retrofit retrofit = null;
    public static Retrofit getApiClient(){
        if(retrofit == null){
             retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Log.e("RetrofitClient", BASE_URL);
        }
        return retrofit;
    }
}
