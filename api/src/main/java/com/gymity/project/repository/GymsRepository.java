package com.gymity.project.repository;

import com.gymity.project.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymsRepository extends JpaRepository<Gym, Long> {
    Gym findByName(String name);
}
