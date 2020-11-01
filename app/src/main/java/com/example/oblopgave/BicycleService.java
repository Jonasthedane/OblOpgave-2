package com.example.oblopgave;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BicycleService {

    //Bicycles

    @GET("Bicycles")
    Call<List<Bicycle>> getAllBicycles();

    @GET("Bicycles/missing")
    Call<List<Bicycle>> getAllMissingBikes();

    @GET("Bicycles/found")
    Call<List<Bicycle>> getAllFoundBikes();

    @POST("Bicycles")
    Call<Bicycle> postBicycle(@Body Bicycle bicycle);

    @DELETE("Bicycles/{id}")
    Call<String> deleteBike(@Path("id") int id);

// Users

    @GET("Users/firebaseuserid/{id}")
    Call<Users> getOneUser(@Path("id") String userId);

    @GET("Users")
    Call<List<Users>> getAllUsers();

    @POST("Users")
    Call<Users> postUser(@Body Users users);

}
