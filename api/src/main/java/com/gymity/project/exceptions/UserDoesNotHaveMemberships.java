package com.gymity.project.exceptions;

public class UserDoesNotHaveMemberships extends Exception {
    public UserDoesNotHaveMemberships() {
        super("User does not have active memberships");
    }
}
