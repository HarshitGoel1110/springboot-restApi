package com.example.restapi.controllers;

import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserAlreadyExistsException;
import com.example.restapi.exceptions.UserNameNotFoundException;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/users")
// By this we are saying that, for every Mapping that we will be using below, append "/users" as prefix
// while writing the API Url
public class UserController {
    @Autowired
    private UserService userService;

    // get User
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // create User
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user , UriComponentsBuilder builder) {
        // here what ever we provide in the request body in the Postman, that will automatically be stored in the "user"
        // for making things happen, advantage of JPA
        try {

            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers , HttpStatus.CREATED);

        } catch (UserAlreadyExistsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }

    // get By Id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
        // @PathVariable -> extract whatever value is given in place of id, in the url
        // and assign that value to "id" variable
        try {
            return userService.getUserById(id);
        }
        catch (UserNotFoundException exception){
            // here we are specifying the Error using the HttpsStatus in order to send the Status codes, like 201, 404
            // and then we can pass some custom String message, but we choose to make it simple and pass the message
            // that comes from the variable itself.
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , exception.getMessage());
        }
    }

    // update By Id
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Long id , @RequestBody User user) {
        try {
            return userService.updateUserById(id, user);
        }
        catch (UserNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }
    // delete by Id
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.deleteUserById(id);
        }
        catch (UserNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }

    @GetMapping("/byUsername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UserNameNotFoundException("UserName not found, kindly provide the correct one");
        }
        return user;
    }

}
