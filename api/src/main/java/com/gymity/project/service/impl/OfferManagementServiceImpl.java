package com.gymity.project.service.impl;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.model.Offer;
import com.gymity.project.repository.GymsRepository;
import com.gymity.project.repository.OffersRepository;
import com.gymity.project.repository.TakenOffersRepository;
import com.gymity.project.repository.UserRepository;
import com.gymity.project.service.OfferManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferManagementServiceImpl implements OfferManagementService {
    private final GymsRepository gymsRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public OfferManagementServiceImpl(GymsRepository gymsRepository, OffersRepository offersRepository, UserRepository userRepository, TakenOffersRepository takenOffersRepository) {
        this.gymsRepository = gymsRepository;
        this.offersRepository = offersRepository;
    }


    @Override
    public void addOffer(Offer offer) throws GymDoesNotExist {
        if (gymsRepository.findByName(offer.gym.name) == null)
            throw new GymDoesNotExist();

        offer.gym = gymsRepository.findByName(offer.gym.name);
        offersRepository.save(offer);
    }


    @Override
    public List<Offer> getAllOffers() {
        return offersRepository.findAll().stream().
                filter(offer -> offer.endOfOffer.isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public Offer getOffer(Long id) {
        return offersRepository.findById(id).get();
    }
}
