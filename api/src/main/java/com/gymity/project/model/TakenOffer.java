package com.gymity.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TakenOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    public Offer offer;


    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    public Users user;

    public LocalDateTime startDate;

    public LocalDateTime getEndDate(){
        return startDate.plusDays(offer.durationInDays);
    }

}
