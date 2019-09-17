package com.gymity.project.model.dto;

import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;

public class OfferDto {
    public Gym gym;
    public Long price;
    public String description;
    public String name;
    public Integer day;
    public Integer month;
    public Integer year;
    public Long durationInDays;

    public OfferDto() {

    }

    public OfferDto(Offer offer) {
        this.gym = offer.gym;
        this.price = offer.price;
        this.description = offer.description;
        this.name = offer.name;
        this.durationInDays = offer.durationInDays;
    }
}
