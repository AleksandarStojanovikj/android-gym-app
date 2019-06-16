package com.gymity.repository;

import com.gymity.model.Credentials;
import com.gymity.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<User> loginUser(@Body Credentials credentials);
}
