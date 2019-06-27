package com.gymity.project.service;

import com.gymity.project.exceptions.InvalidCredentials;
import com.gymity.project.exceptions.UserAlreadyExists;
import com.gymity.project.exceptions.UserDoesNotExist;
import com.gymity.project.exceptions.UserDoesNotHaveMemberships;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Users;

import java.util.ArrayList;

public interface UserManagementService {
    void register(Users user) throws UserAlreadyExists;

    Users login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist;

    ArrayList<Users> getAllUsers();

    ArrayList<Gym> getGymsForUser(String username) throws UserDoesNotExist, UserDoesNotHaveMemberships;
}
