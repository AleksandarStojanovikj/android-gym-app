package com.gymity.project.web;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Credentials;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class UsersController {

    private final UserManagementService userManagementService;

    @Autowired
    public UsersController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestBody Credentials credentials) {
        try {
            Users user = userManagementService.login(credentials);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (InvalidCredentials | UserDoesNotExist invalidCredentials) {
            invalidCredentials.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        try {
            userManagementService.register(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<Users>> getAllUsers() {
        ArrayList<Users> users = userManagementService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{username}/gyms")
    public ResponseEntity<ArrayList<Gym>> getGymsForUser(@PathVariable String username) {
        try {
            ArrayList<Gym> gyms = userManagementService.getGymsForUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(gyms);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/users/{username}/offers")
    public ResponseEntity<ArrayList<Offer>> getOffersForUser(@PathVariable String username) {
        try {
            ArrayList<Offer> offers = userManagementService.getOffersForUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

<<<<<<< HEAD
    @PostMapping("/users/{username}/subscribe-to-gym")
    public ResponseEntity subscribeToGym(@PathVariable String username, @RequestBody Gym gym) {
        try {
            userManagementService.subscribeToGym(username, gym);
            return ResponseEntity.status(HttpStatus.OK).body("User has subscribed!");
        } catch (GymDoesNotExist gymDoesNotExist) {
            gymDoesNotExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gymDoesNotExist.message);
        } catch (UserHasAlreadySubscribedToGym userHasAlreadySubscribedToGym) {
            userHasAlreadySubscribedToGym.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userHasAlreadySubscribedToGym.getMessage());
        } catch (UserDoesNotExist userDoesNotExist) {
            userDoesNotExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDoesNotExist.message);
        }
    }

    @PostMapping("/users/{username}/subscribe-to-offer")
    public ResponseEntity subscribeToOffer(@PathVariable String username, @RequestBody Offer offer) {
        try {
            userManagementService.subscribeToOffer(username, offer);
            return ResponseEntity.status(HttpStatus.OK).body("User has subscribed!");
        } catch (GymDoesNotExist gymDoesNotExist) {
            gymDoesNotExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gymDoesNotExist.message);
        } catch (UserDoesNotExist userDoesNotExist) {
            userDoesNotExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDoesNotExist.message);
        } catch (UserHasAlreadySubscribedToOffer userHasAlreadySubscribedToOffer) {
            userHasAlreadySubscribedToOffer.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userHasAlreadySubscribedToOffer.getMessage());
        } catch (OfferDoesNotExist offerDoesNotExist) {
            offerDoesNotExist.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(offerDoesNotExist.getMessage());
        }
    }
=======
>>>>>>> 954aca740d1d3e180c8554f999851d0d708805c0
}
