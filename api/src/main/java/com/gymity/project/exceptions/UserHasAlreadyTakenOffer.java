package com.gymity.project.exceptions;

public class UserHasAlreadyTakenOffer extends Exception {
    public String message;

    public UserHasAlreadyTakenOffer(){
        this.message = "The user has already taken this offer";
    }
}
