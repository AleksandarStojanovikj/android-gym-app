package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TakenOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    @JsonBackReference(value = "offer_reference")
    public Offer offer;


    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @JsonBackReference(value = "user_reference")
    public Users user;

    public LocalDateTime startDate;

    public LocalDateTime getEndDate(){
        return startDate.plusDays(offer.durationInDays);
    }

}
