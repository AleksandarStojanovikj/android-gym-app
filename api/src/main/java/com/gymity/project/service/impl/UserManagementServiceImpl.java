package com.gymity.project.service.impl;

import com.gymity.project.exceptions.InvalidCredentials;
import com.gymity.project.exceptions.UserAlreadyExists;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.exceptions.UserDoesNotHaveMemberships;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Membership;
import com.gymity.project.model.Users;
import com.gymity.project.repository.MembershipRepository;
import com.gymity.project.repository.UserRepository;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public void register(Users user) throws UserAlreadyExists {
        if (userRepository.findByCredentialsUsername(user.credentials.username) != null)
            throw new UserAlreadyExists();
        userRepository.save(user);
        user.login();
    }

    @Override
    public Users login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist {
        if (userRepository.findByCredentialsUsername(credentials.username) == null)
            throw new UserDoesNotExist();
        if (userRepository.findByCredentials(credentials) == null)
            throw new InvalidCredentials();

        Users user = userRepository.findByCredentials(credentials);
        user.login();
        return user;
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        return (ArrayList<Users>) userRepository.findAll();
    }

    @Override
    public ArrayList<Gym> getGymsForUser(String username) throws UserDoesNotExist, UserDoesNotHaveMemberships {
        Users user = userRepository.findByCredentialsUsername(username);

        if (user == null)
            throw new UserDoesNotExist();

        ArrayList<Membership> userMemberships = membershipRepository.findAllByUsersId(user.id);

        if (userMemberships == null || userMemberships.isEmpty())
            throw new UserDoesNotHaveMemberships();

        ArrayList<Gym> userGyms = new ArrayList<>();

        userMemberships.forEach(userMembership -> userGyms.add(userMembership.gym));

        return userGyms;
    }

}
