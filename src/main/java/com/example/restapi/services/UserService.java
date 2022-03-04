package com.example.restapi.services;

import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserAlreadyExistsException;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // get all Users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // create a new User
    public User createUser(User user) throws UserAlreadyExistsException{
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser != null){
            // means already exists with same Username, but username must be unique
            throw new UserAlreadyExistsException("User Already Exists");
        }
        return userRepository.save(user);
    }

    // get User by Id
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        // We are making use of Optional because
        // we might get null for a given Id. So dealing with it.
        Optional<User> user = Optional.of(userRepository.getById(id));
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found in User Repository");
        }
        return user;
    }

    // update User By Id
    public User updateUserById(Long id , User user) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found in User Repository, kindly provide correct details");
        }
        user.setUserId(id);
        return userRepository.save(user);
    }

    // delete User by Id
    public void deleteUserById(Long id) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found Exception");
        }
        userRepository.deleteById(id);
    }

    // find User by Username
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
