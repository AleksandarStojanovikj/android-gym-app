package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
//    @JsonBackReference
    public List<Offer> offers;

    public String name;
    public String location;

}
