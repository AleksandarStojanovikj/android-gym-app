package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gymity.project.model.dto.OfferDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<TakenOffer> takenOffers;

    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id")
    @JsonBackReference
    public Gym gym;

    public Long price;
    public String description;
    public String name;
    public LocalDateTime endOfOffer;
    public Long durationInDays;

    public Offer(OfferDto offerDto) {
        this.gym = offerDto.gym;
        this.price = offerDto.price;
        this.description = offerDto.description;
        this.durationInDays = offerDto.durationInDays;
        this.name = offerDto.name;
        if (offerDto.year == null || offerDto.month == null || offerDto.day == null)
            this.endOfOffer = LocalDateTime.now().plusDays(10);
        else
            this.endOfOffer = LocalDateTime.of(offerDto.year, offerDto.month, offerDto.day, 0, 0);
    }

    public Offer(){}
}
