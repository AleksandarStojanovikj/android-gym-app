package com.gymity.project.service.impl;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Offer;
import com.gymity.project.repository.GymsRepository;
import com.gymity.project.repository.OffersRepository;
import com.gymity.project.service.OfferManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferManagementServiceImpl implements OfferManagementService {
    private final GymsRepository gymsRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public OfferManagementServiceImpl(GymsRepository gymsRepository, OffersRepository offersRepository) {
        this.gymsRepository = gymsRepository;
        this.offersRepository = offersRepository;
    }


    @Override
    public void addOffer(Offer offer) throws GymDoesNotExist {
        if(gymsRepository.findByName(offer.gym.name) == null)
            throw new GymDoesNotExist();

        offersRepository.save(offer);
    }
}
