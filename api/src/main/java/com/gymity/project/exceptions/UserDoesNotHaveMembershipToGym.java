package com.gymity.project.exceptions;

public class UserDoesNotHaveMembershipToGym extends Exception {
    private String message;

    public UserDoesNotHaveMembershipToGym() {
        this.message = "User has no membership to this gym!";
    }

    public String getMessage() {
        return this.message;
    }
}
