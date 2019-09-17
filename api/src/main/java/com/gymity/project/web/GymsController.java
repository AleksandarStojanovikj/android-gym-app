package com.gymity.project.web;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Comment;
import com.gymity.project.model.Gym;
import com.gymity.project.model.Offer;
import com.gymity.project.model.Users;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.service.GymManagementService;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/gyms", produces = MediaType.APPLICATION_JSON_VALUE)
public class GymsController {
    private final GymManagementService gymManagementService;
    private final UserManagementService userManagementService;

    @Autowired
    public GymsController(GymManagementService gymManagementService, UserManagementService userManagementService) {
        this.gymManagementService = gymManagementService;
        this.userManagementService = userManagementService;
    }

    @PostMapping
    public ResponseEntity<?> addGym(@RequestBody Gym gym) {
        try {
            gymManagementService.addGym(gym);
            return ResponseEntity.status(HttpStatus.OK).body(gym);
        } catch (GymAlreadyExists gymAlreadyExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gymAlreadyExists.message);
        }
    }

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms() {
        return ResponseEntity.status(HttpStatus.OK).body(gymManagementService.getAllGyms());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Gym> getGym(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gymManagementService.getGym(id));
        } catch (GymDoesNotExist gymDoesNotExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value = "{id}")
    public ResponseEntity subscribe(@PathVariable Long id, @RequestBody Users user) {
        try {
            userManagementService.subscribeToGym(user.credentials.username, gymManagementService.getGym(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserHasAlreadySubscribedToGym | GymDoesNotExist userHasAlreadySubscribedToGym) {
            userHasAlreadySubscribedToGym.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (UserDoesNotExist userDoesNotExist) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<List<Gym>> getGymsForUser(@PathVariable String username) {
        try {
            List<Gym> gyms = userManagementService.getGymsForUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(gyms);
        } catch (UserDoesNotHaveMemberships | UserDoesNotExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }

    @GetMapping(value = "/{id}/comments")
    public ResponseEntity<List<Comment>> getGymComments(@PathVariable Long id) {
        try {
            List<Comment> comments = gymManagementService.getGymComments(id);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (GymDoesNotExist gymDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }

    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<OfferDto>> getGymOffers(@PathVariable Long id) {
        try {
            List<OfferDto> offers = gymManagementService.getGymOffers(id);
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } catch (GymDoesNotExist gymDoesNotExist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }


    @PostMapping(value = "/{id}/comments")
    public ResponseEntity<Comment> addCommentForGym(@PathVariable Long id, @RequestBody Comment comment) {
        try {
            Comment commentToSave = gymManagementService.addCommentForGym(id, comment);
            return ResponseEntity.status(HttpStatus.OK).body(commentToSave);
        } catch (UserDoesNotExist | GymDoesNotExist exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }
}
