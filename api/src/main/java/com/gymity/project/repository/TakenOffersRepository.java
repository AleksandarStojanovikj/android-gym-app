package com.gymity.project.repository;

import com.gymity.project.model.Offer;
import com.gymity.project.model.TakenOffer;
import com.gymity.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TakenOffersRepository extends JpaRepository<TakenOffer, Long> {
    ArrayList<TakenOffer> findAllByUserId(Long id);
    TakenOffer findByUserAndOffer(Users user, Offer offer);
}
