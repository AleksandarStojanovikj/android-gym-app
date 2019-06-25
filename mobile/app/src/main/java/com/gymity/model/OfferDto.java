package com.gymity.model;

public class OfferDto {
    public Gym gym;
    public String name;
    public String description;
    public Long durationInDays;
    public Long price;
    public Integer endOfOfferYear;
    public Integer endOfOfferMonth;
    public Integer endOfOfferDay;

    public OfferDto(Gym gym, String name, String description, Long durationInDays, Long price, Integer endOfOfferYear, Integer endOfOfferMonth, Integer endOfOfferDay) {
        this.gym = gym;
        this.name = name;
        this.description = description;
        this.durationInDays = durationInDays;
        this.price = price;
        this.endOfOfferYear = endOfOfferYear;
        this.endOfOfferMonth = endOfOfferMonth;
        this.endOfOfferDay = endOfOfferDay;
    }
}
