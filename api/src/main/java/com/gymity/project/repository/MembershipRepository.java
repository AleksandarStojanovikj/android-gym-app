package com.gymity.project.repository;

import com.gymity.project.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    ArrayList<Membership> findAllByUsersId(Long id);
}
