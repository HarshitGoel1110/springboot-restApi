package com.example.restapi.controllers;

import com.example.restapi.dtos.UserDtoV1;
import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.mappers.UserMapper;
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
@RequestMapping("/versioning/params/users")
@Validated
public class UserRequestParameterVersioningController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // for version 1
    @GetMapping(value = "/{id}" , params = "version=1")
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

    // for version 2
    @GetMapping(value = "/{id}" , params = "version=2")
    public UserDtoV1 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
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

}
