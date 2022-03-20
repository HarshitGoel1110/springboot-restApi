package com.example.restapi.controllers;

import com.example.restapi.dtos.UserDtoV1;
import com.example.restapi.dtos.UserDtoV2;
import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/versioning/uri/users")
@Validated
public class UserUriVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // version v1.0 and v1.1 specifically, It doesn't mean the range.
    @GetMapping({"/v1.0/{id}" , "/v1.1/{id}"})
    public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("User is not present with the given userId");

        User user = optionalUser.get();

        // Converting any Object from "User" class to "UserDto" class
        // in the arguments
        // 1-> is the object, that we want to convert
        // 2-> is the type of the Destination Object, to which we want to convert
        UserDtoV1 userDtoV1 = modelMapper.map(user , UserDtoV1.class);

        return userDtoV1;
    }

    // creating the API for version v2
    @GetMapping("/v2.0/{id}")
    public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("User is not present with the given userId");

        User user = optionalUser.get();

        // Converting any Object from "User" class to "UserDto" class
        // in the arguments
        // 1-> is the object, that we want to convert
        // 2-> is the type of the Destination Object, to which we want to convert
        UserDtoV2 userDtoV2 = modelMapper.map(user , UserDtoV2.class);

        return userDtoV2;
    }

}
