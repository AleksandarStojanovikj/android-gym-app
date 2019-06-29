package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "gym_reference")
    public List<Membership> memberships;

    @OneToMany(mappedBy = "gym", orphanRemoval = true)
    @JsonBackReference
    public List<Offer> offers;

    public String name;
    public String location;

}
