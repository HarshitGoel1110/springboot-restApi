package com.example.restapi.repositories;

import com.example.restapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    // findByUsername -> is a method that is not provided by JpaRepository
    // So to use it, we need to write it down here
    // and that's it

    User findByUsername(String username);
}
