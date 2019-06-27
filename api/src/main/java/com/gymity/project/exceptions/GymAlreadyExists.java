package com.gymity.project.exceptions;

public class GymAlreadyExists extends Exception {
    public String message;

    public GymAlreadyExists() {
        this.message = "Gym already exists";
    }
}
