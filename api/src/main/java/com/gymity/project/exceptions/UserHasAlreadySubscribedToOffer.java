package com.gymity.project.exceptions;

public class UserHasAlreadySubscribedToOffer extends Exception {
    private String message;

    public UserHasAlreadySubscribedToOffer() {
        this.message = "User has already subscribed to offer!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
