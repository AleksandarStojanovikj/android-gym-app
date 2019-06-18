package com.gymity.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @OneToMany(mappedBy = "offer")
    public List<TakenOffer> takenOffers;

    @ManyToOne
    @JoinColumn(name = "gym_id", referencedColumnName = "id")
    public Gym gym;

    public Long price;
    public String description;
    public String name;
    public LocalDateTime endOfOffer;
    public Long durationInDays;
}
