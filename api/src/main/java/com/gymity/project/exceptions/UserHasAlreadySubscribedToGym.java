package com.gymity.project.exceptions;

public class UserHasAlreadySubscribedToGym extends Exception {
    private String message;

    public UserHasAlreadySubscribedToGym() {
        this.message = "User has already subscribed to this gym!";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
