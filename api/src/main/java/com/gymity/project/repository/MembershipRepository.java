package com.gymity.project.repository;

import com.gymity.project.model.Gym;
import com.gymity.project.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    ArrayList<Membership> findAllByUsersId(Long id);
    ArrayList<Membership> findAllByUsersIdAndAndGym(Long id, Gym gym);
}
