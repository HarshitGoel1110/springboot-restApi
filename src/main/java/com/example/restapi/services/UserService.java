package com.example.restapi.services;

import com.example.restapi.entities.User;
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
    public User createUser(User user){
        return userRepository.save(user);
    }

    // get User by Id
    public User getUserById(Long id){
        // We are making use of Optional because
        // we might get null for a given Id. So dealing with it.
        User user = userRepository.getById(id);
        return user;
    }

    // update User By Id
    public User updateUserById(Long id , User user){
        user.setId(id);
        return userRepository.save(user);
    }

    // delete User by Id
    public void deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    // find User by Username
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
