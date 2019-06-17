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

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(Users user) throws UserAlreadyExists {
        if (userRepository.findByCredentials(user.credentials) != null)
            throw new UserAlreadyExists();
        userRepository.save(user);
        user.login();
    }

    @Override
    public void login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist {
        if(userRepository.findByCredentialsUsername(credentials.username) == null)
            throw new UserDoesNotExist();
        if(userRepository.findByCredentials(credentials) == null)
            throw new InvalidCredentials();

        userRepository.findByCredentials(credentials).login();
    }
}
