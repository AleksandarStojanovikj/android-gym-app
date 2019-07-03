package com.gymity.project.exceptions;

public class UserHasAlreadySubscribedToGym extends Exception {
    public String message;

    public UserHasAlreadySubscribedToGym(){
        this.message = "The user has already subscribed to this gym";
    }
}
