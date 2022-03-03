package com.example.restapi.controllers;

import com.example.restapi.entities.Order;
import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.repositories.OrderRepository;
import com.example.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable("userId") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User is not present");
        }
        return user.get().getOrders();
    }

    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable("userId") Long userId , @RequestBody Order order) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User is not Present");
        }
        User user = optionalUser.get();
        order.setUser(user);
        orderRepository.save(order);
        return order;
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public Order getOrderByOrderId(@PathVariable("userId") Long userId , @PathVariable("orderId") Long orderId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not exists");
        }
        User user = optionalUser.get();
        List<Order> order = user.getOrders();
        for(Order o : order){
            if(o.getOrderId().equals(orderId)){
                return o;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND , "The orderId doesn't exists via a particular user");
    }

}
