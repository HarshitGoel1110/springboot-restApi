package com.example.restapi.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // there are two methods for it,
    // 1-> RequestMapping
    // 2-> GetMapping
//    @RequestMapping(method = RequestMethod.GET , path = "/helloWorld")
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World1";
    }

    @GetMapping("/helloWorld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Harshit" , "Goel" , "Ghaziabad");
    }

}
