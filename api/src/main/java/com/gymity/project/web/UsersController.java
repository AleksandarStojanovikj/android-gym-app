package com.gymity.project.web;

import com.gymity.project.model.Credentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UsersController {

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Credentials credentials){
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }
}
