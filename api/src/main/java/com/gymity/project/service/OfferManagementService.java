package com.gymity.project.service;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Offer;

import java.util.List;

public interface OfferManagementService {
    void addOffer(Offer offer) throws GymDoesNotExist;
    List<Offer> getAllOffers();
}
