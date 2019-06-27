package com.gymity.project.exceptions;

public class GymDoesNotExist extends Exception {
    public String message;

    public GymDoesNotExist() {
        this.message = "Gym does not exist";
    }
}
