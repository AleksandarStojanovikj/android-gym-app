package com.gymity.project.model;

import javax.persistence.Embeddable;

@Embeddable
public class Credentials {
    public String username;
    public String password;
}
