package com.gymity.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Credentials {
    @Column(unique = true)
    public String username;
    public String password;
}
