package com.gymity.project.exceptions;

public class UserHasAlreadySubscribedToGym extends Exception {
<<<<<<< HEAD
    private String message;

    public UserHasAlreadySubscribedToGym() {
        this.message = "User has already subscribed to this gym!";
    }

    @Override
    public String getMessage() {
        return message;
=======
    public String message;

    public UserHasAlreadySubscribedToGym(){
        this.message = "The user has already subscribed to this gym";
>>>>>>> 954aca740d1d3e180c8554f999851d0d708805c0
    }
}
