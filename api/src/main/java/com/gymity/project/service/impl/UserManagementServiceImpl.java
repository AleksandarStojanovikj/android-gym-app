package com.gymity.project.service.impl;

import com.gymity.project.exceptions.InvalidCredentials;
import com.gymity.project.exceptions.UserAlreadyExists;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Users;
import com.gymity.project.repository.UserRepository;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
