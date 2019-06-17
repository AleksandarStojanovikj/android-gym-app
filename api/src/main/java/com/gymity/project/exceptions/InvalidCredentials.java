package com.gymity.project.exceptions;

public class InvalidCredentials extends Exception {
    public String message;

    public InvalidCredentials(){
        this.message = "Invalid credentials";
    }
}
