package com.gymity.project.repository;

import com.gymity.project.model.Credentials;
import com.gymity.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByCredentials(Credentials credentials);
    Users findByCredentialsUsername(String username);
}
