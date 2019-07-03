package com.gymity.project.repository;

import com.gymity.project.model.Gym;
import com.gymity.project.model.Membership;
import com.gymity.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    ArrayList<Membership> findAllByUsersId(Long id);
    Membership findByUsersAndAndGym(Users user, Gym gym);
}
