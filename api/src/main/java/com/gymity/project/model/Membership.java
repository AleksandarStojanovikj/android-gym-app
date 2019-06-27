package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties
public class Membership {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonBackReference(value = "gym_reference")
    public Gym gym;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonBackReference
    public Users users;
}
