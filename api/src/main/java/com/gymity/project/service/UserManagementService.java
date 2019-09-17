package com.gymity.project.service;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;
import com.gymity.project.model.dto.OfferDto;

import java.util.ArrayList;
import java.util.List;

public interface UserManagementService {
    void register(Users user) throws UserAlreadyExists;

    Users login(Credentials credentials) throws InvalidCredentials, UserDoesNotExist;

    ArrayList<Users> getAllUsers();

    ArrayList<Gym> getGymsForUser(String username) throws UserDoesNotExist, UserDoesNotHaveMemberships;

    List<OfferDto> getOffersForUser(String username) throws UserDoesNotExist, UserDoesNotHaveOffers;

    void subscribeToGym(String username, Gym gym) throws UserDoesNotExist, UserHasAlreadySubscribedToGym, GymDoesNotExist;

    void subscribeToOffer(String username, Offer offer) throws UserDoesNotExist, GymDoesNotExist, UserHasAlreadySubscribedToOffer, OfferDoesNotExist;

    void unsubscribeFromGym(String username, String gymName) throws UserDoesNotExist, GymDoesNotExist, UserDoesNotHaveMembershipToGym;
}
