package com.gymity.project.model;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    public Credentials credentials;

    @OneToMany(mappedBy = "user")
    public List<TakenOffer> myOffers;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Membership> memberships;

    public String fullName;
    public String accessToken;
    public boolean isAdmin;

    public void login() {
        this.accessToken = new UID().toString();
    }
}
