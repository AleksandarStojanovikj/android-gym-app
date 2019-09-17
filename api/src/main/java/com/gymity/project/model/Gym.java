package com.gymity.project.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "gym_reference")
    public List<Membership> memberships;

    @OneToMany(mappedBy = "gym")
    @JsonManagedReference(value = "gym_comment_reference")
    public List<Comment> comments;

    @OneToMany(mappedBy = "gym", orphanRemoval = true)
    @JsonManagedReference(value = "offers_gym_reference")
    public List<Offer> offers;

    public String name;
    public String location;

}
