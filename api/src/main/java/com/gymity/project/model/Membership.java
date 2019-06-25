package com.gymity.project.model;

import javax.persistence.*;

@Entity
public class Membership {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    public Gym gym;

    @ManyToOne
    @JoinColumn(name = "users_id")
    public Users users;
}
