package com.gymity.project.web;

import com.gymity.project.exceptions.GymDoesNotExist;
import com.gymity.project.exceptions.ObjectDoesNotExist;
import com.gymity.project.model.Offer;
import com.gymity.project.model.dto.OfferDto;
import com.gymity.project.service.OfferManagementService;
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

    @Autowired
    public OffersController(OfferManagementService offerManagementService) {
        this.offerManagementService = offerManagementService;
    }

    @PostMapping
    public ResponseEntity<Offer> addOffer(@RequestBody OfferDto offerDto) {
        try {
            Offer offer = new Offer(offerDto);
            offerManagementService.addOffer(offer);
            return ResponseEntity.status(HttpStatus.OK).body(offer);
        } catch (GymDoesNotExist exception) {
            System.out.println(exception.message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.status(HttpStatus.OK).body(offerManagementService.getAllOffers());
    }
}
