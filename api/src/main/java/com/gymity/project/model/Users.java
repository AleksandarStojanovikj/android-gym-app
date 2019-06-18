package com.gymity.project.model;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.List;

@Entity(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    public Credentials credentials;

    @OneToMany(mappedBy = "user")
    public List<TakenOffer> myOffers;

    @ManyToMany
    @JoinTable(name = "gym", joinColumns = @JoinColumn(name = "gym_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    public List<Gym> myGyms;

    public String fullName;
    public String accessToken;
    public boolean isAdmin;

    public void login() {
        this.accessToken = new UID().toString();
    }
}
