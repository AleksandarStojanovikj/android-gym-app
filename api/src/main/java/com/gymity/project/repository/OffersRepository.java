package com.gymity.project.repository;

import com.gymity.project.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffersRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByName(String name);
}
