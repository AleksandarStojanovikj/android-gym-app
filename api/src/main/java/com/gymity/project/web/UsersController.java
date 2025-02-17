package com.gymity.project.web;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UsersController {

    private final UserManagementService userManagementService;

    @Autowired
    public UsersController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Credentials credentials) {
        try {
            Users user = userManagementService.login(credentials);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (InvalidCredentials | UserDoesNotExist invalidCredentials) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidCredentials.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        try {
            userManagementService.register(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (UserAlreadyExists userAlreadyExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<Users>> getAllUsers() {
        ArrayList<Users> users = userManagementService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{username}/gyms")
    public ResponseEntity<List<Gym>> getGymsForUser(@PathVariable String username) {
        try {
            List<Gym> gyms = userManagementService.getGymsForUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(gyms);
        } catch (UserDoesNotHaveMemberships | UserDoesNotExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

    }

    @GetMapping("/users/{username}/offers")
    public ResponseEntity<List<OfferDto>> getOffersForUser(@PathVariable String username) {
        try {
            List<OfferDto> offers = userManagementService.getOffersForUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/users/{username}/subscribe-to-gym")
    public ResponseEntity<?> subscribeToGym(@PathVariable String username, @RequestBody Gym gym) {
        try {
            userManagementService.subscribeToGym(username, gym);
            return ResponseEntity.status(HttpStatus.OK).body(gym);
        } catch (GymDoesNotExist gymDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gymDoesNotExist.message);
        } catch (UserHasAlreadySubscribedToGym userHasAlreadySubscribedToGym) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userHasAlreadySubscribedToGym.getMessage());
        } catch (UserDoesNotExist userDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDoesNotExist.message);
        }
    }

    @PostMapping("/users/{username}/unsubscribe")
    public ResponseEntity<?> unsubscribeFromGym(@PathVariable String username, @RequestBody Gym gym) {
        try {
            userManagementService.unsubscribeFromGym(username, gym.name);
            return ResponseEntity.status(HttpStatus.OK).body("User has successfully unsubscribed from gym");
        } catch (UserDoesNotHaveMembershipToGym | GymDoesNotExist | UserDoesNotExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/users/{username}/subscribe-to-offer")
    public ResponseEntity<?> subscribeToOffer(@PathVariable String username, @RequestBody Offer offer) {
        try {
            userManagementService.subscribeToOffer(username, offer);
            return ResponseEntity.status(HttpStatus.OK).body(offer);
        } catch (GymDoesNotExist gymDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gymDoesNotExist.message);
        } catch (UserDoesNotExist userDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDoesNotExist.message);
        } catch (UserHasAlreadySubscribedToOffer userHasAlreadySubscribedToOffer) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userHasAlreadySubscribedToOffer.getMessage());
        } catch (OfferDoesNotExist offerDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(offerDoesNotExist.getMessage());
        }
    }
}
