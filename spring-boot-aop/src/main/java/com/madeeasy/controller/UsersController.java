package com.madeeasy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @GetMapping("/users")
    public String getAllUsers() {
        System.out.println("Get all users from users controller class");
        return "success";
    }

    @GetMapping("/users/exceptions")
    public String getAllUsersExceptions() {
        System.out.println("Get all users from users controller class");
        throw new RuntimeException("Simulating an exception in getAllUsers()");
    }
}
