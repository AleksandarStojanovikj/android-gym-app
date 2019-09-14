package com.gymity.repository;

import com.gymity.model.Credentials;
import com.gymity.model.Gym;
import com.gymity.model.OfferDto;
import com.gymity.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserClient {

    @POST("login")
    Call<Users> loginUser(@Body Credentials credentials);

    @POST("register")
    Call<Users> registerUser(@Body Users user);

    @GET("users")
    Call<List<Users>> getAllUsers();

    @POST("users/{username}/subscribe-to-offer")
    Call<OfferDto> subscribeToOffer(@Path("username") String username, @Body OfferDto offerDto);

    @POST("users/{username}/subscribe-to-gym")
    Call<Gym> subscribeToGym(@Path("username") String username, @Body Gym gym);

    @POST("users/{username}/{gymName}")
    Call<String> unsubscribeFromGym(@Path("username") String username, @Path("gymName") String gymName);

    @GET("users/{username}/offers")
    Call<List<OfferDto>> getMyOffers(@Path("username") String username);

    @GET("users/{username}/gyms")
    Call<List<Gym>> getMyGyms(@Path("username") String username);
}
