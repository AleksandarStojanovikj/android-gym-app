package com.gymity.project.repository;

import com.gymity.project.model.Gym;
import com.gymity.project.model.Membership;
import com.gymity.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    ArrayList<Membership> findAllByUsersId(Long id);

    ArrayList<Membership> findAllByUsersIdAndGym(Long id, Gym gym);

    ArrayList<Membership> findAllByUsersCredentialsUsernameAndGymName(String username, String gymName);

    Membership findByUsersAndGym(Users user, Gym gym);
}
