package com.example.oblopgave;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BicycleService {

    @GET("bicycles")
    Call<List<Bicycle>> getAllBicycles();

    @GET("bicycles/missing")
    Call<List<Bicycle>> getAllMissingBikes();

    @GET("Bicycles/found")
    Call<List<Bicycle>> getAllFoundBikes();

}
