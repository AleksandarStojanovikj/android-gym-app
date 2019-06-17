package com.gymity.project.service;

import com.gymity.project.exceptions.InvalidCredentials;
import com.gymity.project.exceptions.UserAlreadyExists;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Users;

public interface UserManagementService {
    void register(Users user) throws UserAlreadyExists;
    Users login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist;
}
