package com.gymity.repository;

import com.gymity.model.OfferDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OfferClient {
    @POST("offers")
    Call<OfferDto> addOffer(@Body OfferDto offerDto);

    @GET("offers")
    Call<List<OfferDto>> getOffers();
}
