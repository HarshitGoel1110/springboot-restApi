package com.example.restapi.controllers;

import com.example.restapi.entities.User;
import com.example.restapi.exceptions.UserNotFoundException;
import com.example.restapi.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id){
        try{
            User user = userService.getUserById(id).get();
            Set<String> fields = new HashSet<>();
            fields.add("userId");
            fields.add("username");
            fields.add("ssn");
            fields.add("orders");

            // below is the code that will filter the fields that we want to display on the postman,
            // fields that we specify in "fields" variable will be the only ones, that will be displayed on postman.
            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("useFilter" , SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            // "useFilter" -> is the id that we must specify on the model class of the user

            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);

            return mapper;

        }
        catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , e.getMessage());
        }
    }

    // getUserById -> via @RequestParam
    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id , @RequestParam Set<String> fields){
        try{
            User user = userService.getUserById(id).get();

            // below is the code that will filter the fields that we want to display on the postman,
            // fields that we specify in "fields" variable will be the only ones, that will be displayed on postman.
            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("useFilter" , SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            // "useFilter" -> is the id that we must specify on the model class of the user

            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);

            return mapper;

        }
        catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , e.getMessage());
        }
    }

}
