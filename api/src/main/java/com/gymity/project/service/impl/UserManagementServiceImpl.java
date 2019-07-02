package com.gymity.project.service.impl;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.*;
import com.gymity.project.repository.MembershipRepository;
import com.gymity.project.repository.TakenOffersRepository;
import com.gymity.project.repository.UserRepository;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final TakenOffersRepository takenOffersRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository, MembershipRepository membershipRepository, TakenOffersRepository takenOffersRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.takenOffersRepository = takenOffersRepository;
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

    @Override
    public ArrayList<Offer> getOffersForUser(String username) throws UserDoesNotExist, UserDoesNotHaveOffers {
        Users user = userRepository.findByCredentialsUsername(username);

        if (user == null)
            throw new UserDoesNotExist();

        ArrayList<TakenOffer> userTakenOffers = takenOffersRepository.findAllByUserId(user.id);

        if (userTakenOffers == null || userTakenOffers.isEmpty())
            throw new UserDoesNotHaveOffers();

        ArrayList<Offer> userOffers = new ArrayList<>();

        userTakenOffers.forEach(takenOffer -> userOffers.add(takenOffer.offer));

        return userOffers;
    }

    @Override
    public void takeOffer(Long userId, Offer offer) throws UserHasAlreadyTakenOffer {
        if(takenOffersRepository.findByUserAndOffer(userRepository.findById(userId).get(), offer) != null){
            throw new UserHasAlreadyTakenOffer();
        }

        TakenOffer takenOffer = new TakenOffer();
        takenOffer.startDate = LocalDateTime.now();
        takenOffer.offer = offer;
        takenOffer.user = userRepository.findById(userId).get();

        takenOffersRepository.save(takenOffer);
    }
}
