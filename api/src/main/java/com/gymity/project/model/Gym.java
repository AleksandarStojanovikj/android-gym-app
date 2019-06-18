package com.gymity.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @ManyToMany(mappedBy = "myGyms")
    public List<Users> users;

    @OneToMany(mappedBy = "gym")
    public List<Offer> offers;

    public String name;
    public String location;

}
