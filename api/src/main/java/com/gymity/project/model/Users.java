package com.gymity.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.List;

@Entity(name = "users")
@JsonIgnoreProperties
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    public Credentials credentials;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user_reference")
    public List<TakenOffer> myOffers;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    public List<Membership> memberships;

    public String fullName;
    public String accessToken;
    public boolean isAdmin;

    public void login() {
        this.accessToken = new UID().toString();
    }

}
