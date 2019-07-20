package com.example.laundrykh.Services;

import com.example.laundrykh.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("register.php")
    Call<User> doRegistration(@Query("name") String name, @Query("email") String email, @Query("phone") String phone, @Query("password") String password);

    @POST("ApiSignIn.php")
    Call<User> doLogin(@Query("email") String email, @Query("password") String password);

}
