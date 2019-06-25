package com.gymity.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Membership> memberships;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Offer> offers;

    public String name;
    public String location;

}
