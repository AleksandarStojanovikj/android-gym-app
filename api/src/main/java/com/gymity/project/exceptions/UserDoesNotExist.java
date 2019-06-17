package com.gymity.project.exceptions;

public class UserDoesNotExist extends Exception {

    public String message;

    public UserDoesNotExist(){
        this.message = "User does not exist!";
    }
}
