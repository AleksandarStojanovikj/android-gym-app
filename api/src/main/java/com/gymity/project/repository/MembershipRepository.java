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
<<<<<<< HEAD
    ArrayList<Membership> findAllByUsersIdAndAndGym(Long id, Gym gym);
=======
    Membership findByUsersAndAndGym(Users user, Gym gym);
>>>>>>> 954aca740d1d3e180c8554f999851d0d708805c0
}
