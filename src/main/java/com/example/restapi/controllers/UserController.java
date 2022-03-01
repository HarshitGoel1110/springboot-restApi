package com.example.restapi.controllers;

import com.example.restapi.entities.User;
import com.example.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // get User
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // create User
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        // here what ever we provide in the request body in the Postman, that will automatically be stored in the "user"
        // for making things happen, advantage of JPA
        return userService.createUser(user);
    }

    // get By Id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id){
        // @PathVariable -> extract whatever value is given in place of id, in the url
        // and assign that value to "id" variable
        return userService.getUserById(id);
    }

    // update By Id
    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id , @RequestBody User user){
        return userService.updateUserById(id , user);
    }

    // delete by Id
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byUsername/{username}")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

}
