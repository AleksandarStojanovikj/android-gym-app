package com.gymity.model;

public class Gym {
    public Long id;
    public String name;
    public String location;

    public Gym(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
