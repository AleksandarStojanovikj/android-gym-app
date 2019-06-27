package com.gymity.project.exceptions;

public class UserAlreadyExists extends Exception {
    public String message;

    public UserAlreadyExists() {
        this.message = "User already exists";
    }
}
