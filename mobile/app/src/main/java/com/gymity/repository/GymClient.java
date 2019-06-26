package com.gymity.repository;

import com.gymity.model.Gym;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GymClient {
    @POST("gyms")
    Call<Gym> addGym(@Body Gym gym);

    @GET
    Call<List<Gym>> getGyms();
}
