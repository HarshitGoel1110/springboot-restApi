package com.example.restapi.controllers;

import com.example.restapi.entities.User;
import com.example.restapi.entities.Views;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {
    @Autowired
    private UserService userService;

    @GetMapping("/external/{id}")
    @JsonView(Views.External.class)
    // By this we are telling the SpringBoot, that this function is for the External View
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        }
        catch (UserNotFoundException exception){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , exception.getMessage());
        }
    }

    @GetMapping("/internal/{id}")
    @JsonView(Views.Internal.class)
    public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        }
        catch (UserNotFoundException exception){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , exception.getMessage());
        }
    }
}
