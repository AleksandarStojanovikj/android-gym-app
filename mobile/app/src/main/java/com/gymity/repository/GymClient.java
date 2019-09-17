package com.gymity.repository;

import com.gymity.model.Comment;
import com.gymity.model.Gym;
import com.gymity.model.OfferDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GymClient {
    @POST("gyms")
    Call<Gym> addGym(@Body Gym gym);

    @GET("gyms")
    Call<List<Gym>> getGyms();

    @GET("gyms/{id}/comments")
    Call<List<Comment>> getGymComments(@Path("id") Long id);

    @GET("gyms/{id}/offers")
    Call<List<OfferDto>> getGymOffers(@Path("id") Long id);

    @POST("gyms/{id}/comments")
    Call<Comment> addCommentToGym(@Path("id") Long id, @Body Comment comment);
}
