package com.gymity.project.repository;

import com.gymity.project.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffersRepository extends JpaRepository<Offer, Long> {
}
