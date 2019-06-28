package com.gymity.repository;

import com.gymity.model.Credentials;
import com.gymity.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<Users> loginUser(@Body Credentials credentials);

    @POST("register")
    Call<Users> registerUser(@Body Users user);

    @GET("users")
    Call<List<Users>> getAllUsers();
}
