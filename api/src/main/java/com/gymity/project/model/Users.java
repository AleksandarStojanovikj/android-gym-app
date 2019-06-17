package com.gymity.project.model;

import javax.persistence.*;
import java.rmi.server.UID;

@Entity(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    public Credentials credentials;

    public String fullName;

    public String accessToken;

    public boolean isAdmin;

    public void login() {
        this.accessToken = new UID().toString();
    }
}
