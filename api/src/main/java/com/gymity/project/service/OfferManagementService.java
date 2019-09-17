package com.gymity.project.service;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Offer;
import com.gymity.project.model.dto.OfferDto;

import java.util.List;

public interface OfferManagementService {
    void addOffer(Offer offer) throws GymDoesNotExist;
    List<OfferDto> getAllOffers();
    Offer getOffer(Long id);
}
