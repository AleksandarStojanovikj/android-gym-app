package com.gymity.project.repository;

<<<<<<< HEAD
import com.gymity.project.model.Gym;
=======
import com.gymity.project.model.Offer;
>>>>>>> 954aca740d1d3e180c8554f999851d0d708805c0
import com.gymity.project.model.TakenOffer;
import com.gymity.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TakenOffersRepository extends JpaRepository<TakenOffer, Long> {
    ArrayList<TakenOffer> findAllByUserId(Long id);
<<<<<<< HEAD
    ArrayList<TakenOffer> findAllByUserIdAndOfferGym(Long id, Gym gym);
=======
    TakenOffer findByUserAndOffer(Users user, Offer offer);
>>>>>>> 954aca740d1d3e180c8554f999851d0d708805c0
}
