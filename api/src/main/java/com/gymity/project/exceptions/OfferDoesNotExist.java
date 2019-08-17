package com.gymity.project.exceptions;

public class OfferDoesNotExist extends Exception {
    private String message;

    public OfferDoesNotExist() {
        this.message = "Offer does not exist";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
