package com.example.restapi.controllers;

import com.example.restapi.dtos.UserMmDto;
import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(value = "/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if(!optionalUser.isPresent())
            throw new UserNotFoundException("User is not present with the given userId");

        User user = optionalUser.get();

        // Converting any Object from "User" class to "UserDto" class
        // in the arguments
        // 1-> is the object, that we want to convert
        // 2-> is the type of the Destination Object, to which we want to convert
        UserMmDto userMmDto = modelMapper.map(user , UserMmDto.class);

        return userMmDto;

    }

}
