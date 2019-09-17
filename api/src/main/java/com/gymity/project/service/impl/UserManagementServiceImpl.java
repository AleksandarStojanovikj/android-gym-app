package com.gymity.project.service.impl;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.*;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.repository.*;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final TakenOffersRepository takenOffersRepository;
    private final GymsRepository gymsRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public UserManagementServiceImpl(UserRepository userRepository, MembershipRepository membershipRepository, TakenOffersRepository takenOffersRepository, GymsRepository gymsRepository, OffersRepository offersRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.takenOffersRepository = takenOffersRepository;
        this.gymsRepository = gymsRepository;
        this.offersRepository = offersRepository;
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
        Users user = Optional.of(userRepository.findByCredentialsUsername(username)).orElseThrow(UserDoesNotExist::new);

        ArrayList<Membership> userMemberships = membershipRepository.findAllByUsersId(user.id);

        if (userMemberships == null || userMemberships.isEmpty())
            throw new UserDoesNotHaveMemberships();

        ArrayList<Gym> userGyms = new ArrayList<>();

        userMemberships.forEach(userMembership -> userGyms.add(userMembership.gym));

        return userGyms;
    }

    @Override
    public List<OfferDto> getOffersForUser(String username) throws UserDoesNotExist, UserDoesNotHaveOffers {
        Users user = userRepository.findByCredentialsUsername(username);

        if (user == null)
            throw new UserDoesNotExist();

        ArrayList<TakenOffer> userTakenOffers = takenOffersRepository.findAllByUserId(user.id);

        if (userTakenOffers == null || userTakenOffers.isEmpty())
            throw new UserDoesNotHaveOffers();

        ArrayList<Offer> userOffers = new ArrayList<>();

        userTakenOffers.forEach(takenOffer -> userOffers.add(takenOffer.offer));

        return userOffers.stream()
                .map(OfferDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void subscribeToGym(String username, Gym gym) throws UserDoesNotExist, UserHasAlreadySubscribedToGym, GymDoesNotExist {
        Users user = userRepository.findByCredentialsUsername(username);
        Gym actualGymForSubscription = gymsRepository.findByName(gym.name);

        if (user == null)
            throw new UserDoesNotExist();

        if (actualGymForSubscription == null)
            throw new GymDoesNotExist();

        if (membershipRepository.findAllByUsersCredentialsUsernameAndGymName(user.credentials.username, gym.name) != null)
            throw new UserHasAlreadySubscribedToGym();


        membershipRepository.save(new Membership(actualGymForSubscription, user));
    }

    @Override
    public void subscribeToOffer(String username, Offer offer) throws UserDoesNotExist, GymDoesNotExist, UserHasAlreadySubscribedToOffer, OfferDoesNotExist {
        Users user = userRepository.findByCredentialsUsername(username);
        Offer actualOfferForSubscription = offersRepository.findByName(offer.name).orElseThrow(OfferDoesNotExist::new);
        Gym actualOfferGym = gymsRepository.findByName(offer.gym.name);

        if (user == null)
            throw new UserDoesNotExist();

        if (actualOfferGym == null)
            throw new GymDoesNotExist();

        if (takenOffersRepository.findAllByUserIdAndOfferGym(user.id, actualOfferGym).size() != 0)
            throw new UserHasAlreadySubscribedToOffer();

        if (membershipRepository.findAllByUsersIdAndGym(user.id, actualOfferGym) == null) {
            membershipRepository.save(new Membership(actualOfferGym, user));
        }

        takenOffersRepository.save(new TakenOffer(actualOfferForSubscription, user));
    }

    @Override
    public void unsubscribeFromGym(String username, String gymName) throws UserDoesNotExist, GymDoesNotExist, UserDoesNotHaveMembershipToGym {
        Optional.of(userRepository.findByCredentialsUsername(username)).orElseThrow(UserDoesNotExist::new);
        Optional.of(gymsRepository.findByName(gymName)).orElseThrow(GymDoesNotExist::new);
        Membership membership = membershipRepository.findAllByUsersCredentialsUsernameAndGymName(username, gymName);

        if (membership == null) {
            throw new UserDoesNotHaveMembershipToGym();
        }

        membershipRepository.delete(membership);
    }
}
