package com.gymity.project.service;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;

import java.util.ArrayList;

public interface UserManagementService {
    void register(Users user) throws UserAlreadyExists;

    Users login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist;

    ArrayList<Users> getAllUsers();

    ArrayList<Gym> getGymsForUser(String username) throws UserDoesNotExist, UserDoesNotHaveMemberships;

    ArrayList<Offer> getOffersForUser(String username) throws UserDoesNotExist, UserDoesNotHaveOffers;

    void subscribeToGym(String username, Gym gym) throws UserDoesNotExist, UserHasAlreadySubscribedToGym, GymDoesNotExist;

    void subscribeToOffer(String username, Offer offer) throws UserDoesNotExist, GymDoesNotExist, UserHasAlreadySubscribedToOffer, OfferDoesNotExist;
}
