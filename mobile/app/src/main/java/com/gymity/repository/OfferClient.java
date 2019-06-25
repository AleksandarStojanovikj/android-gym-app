package com.gymity.repository;

import com.gymity.model.OfferDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OfferClient {
    @POST("offers")
    Call<OfferDto> addOffer(@Body OfferDto offerDto);
}
