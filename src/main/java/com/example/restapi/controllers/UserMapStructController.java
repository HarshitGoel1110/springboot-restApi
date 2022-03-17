package com.example.restapi.controllers;

import com.example.restapi.dtos.UserMsDto;
import com.example.restapi.entities.User;
import com.example.restapi.mappers.UserMapper;
import com.example.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mapstruct/users")
@Validated
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public List<UserMsDto> getAllUserDtos(){
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserMsDtos(users);
    }

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable("id") Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return userMapper.userToUserMsDto(user);
    }


}
