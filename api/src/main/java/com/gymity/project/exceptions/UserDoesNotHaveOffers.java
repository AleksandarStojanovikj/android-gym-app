package com.gymity.project.exceptions;

public class UserDoesNotHaveOffers extends Exception {
    public UserDoesNotHaveOffers() {
        super("User does not have any offers");
    }
}
