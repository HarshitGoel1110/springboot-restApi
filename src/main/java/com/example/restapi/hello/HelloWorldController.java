package com.example.restapi.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    // there are two methods for it,
    // 1-> RequestMapping
    // 2-> GetMapping
//    @RequestMapping(method = RequestMethod.GET , path = "/helloWorld")

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World1";
    }

    @GetMapping("/helloWorld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Harshit" , "Goel" , "Ghaziabad");
    }

    @GetMapping("/hello-int")
    public String getMessageInI18NFormat(@RequestHeader(name = "Accept-Language" , required = false) String locale){
        return messageSource.getMessage("label.hello" , null , new Locale(locale));
    }

    @GetMapping("/hello-int2")
    public String getMessageInI18NFormat(){
        return messageSource.getMessage("label.hello" , null , LocaleContextHolder.getLocale());
    }

}
