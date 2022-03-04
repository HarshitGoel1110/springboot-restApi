package com.example.restapi.controllers;

import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.repositories.UserRepository;
import com.example.restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // get User
    @GetMapping
    public List<User> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        for(User user : allUsers){
            Long userId = user.getUserId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);
        }
        return allUsers;
    }

    // get By Id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @Min(1) Long id) {
        // @PathVariable -> extract whatever value is given in place of id, in the url
        // and assign that value to "id" variable
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();
            Long userId = user.getUserId();
            // here we are specifying that this link that we are providing has link to itself
            // that is self linking
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);
            ResponseEntity<User> responseEntity = new ResponseEntity<>(user , HttpStatus.ACCEPTED);
            return responseEntity;
        }
        catch (UserNotFoundException exception){
            // here we are specifying the Error using the HttpsStatus in order to send the Status codes, like 201, 404
            // and then we can pass some custom String message, but we choose to make it simple and pass the message
            // that comes from the variable itself.
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , exception.getMessage());
        }
    }


}
