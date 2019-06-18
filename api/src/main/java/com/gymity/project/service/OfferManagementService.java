package com.gymity.project.service;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Offer;

public interface OfferManagementService {
    void addOffer(Offer offer) throws GymDoesNotExist;
}
