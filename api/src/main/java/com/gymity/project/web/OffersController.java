package com.gymity.project.web;

import com.gymity.project.exceptions.*;
import com.gymity.project.model.Offer;
import com.gymity.project.model.TakenOffer;
import com.gymity.project.model.Users;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.service.OfferManagementService;
import com.gymity.project.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
public class OffersController {
    private final OfferManagementService offerManagementService;
    private final UserManagementService userManagementService;

    @Autowired
    public OffersController(OfferManagementService offerManagementService, UserManagementService userManagementService) {
        this.offerManagementService = offerManagementService;
        this.userManagementService = userManagementService;
    }

    @PostMapping
    public ResponseEntity<Offer> addOffer(@RequestBody OfferDto offerDto) {
        try {
            Offer offer = new Offer(offerDto);
            offerManagementService.addOffer(offer);
            return ResponseEntity.status(HttpStatus.OK).body(offer);
        } catch (GymDoesNotExist exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.status(HttpStatus.OK).body(offerManagementService.getAllOffers());
    }

    @PostMapping("/{offerId}")
    public ResponseEntity takeOfferForUser(@PathVariable Long offerId, @RequestBody Users user) {
        try {
            userManagementService.subscribeToOffer(user.credentials.username, offerManagementService.getOffer(offerId));
            return new ResponseEntity(HttpStatus.OK);
        } catch (GymDoesNotExist | UserDoesNotExist | OfferDoesNotExist exception) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (UserHasAlreadySubscribedToOffer userHasAlreadySubscribedToOffer) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
