package com.example.restapi.entities;

public class Views {
    public static class External{

    }

    public static class Internal extends External{
        // here we are extending External class because, we want all the features of that class in this class
        // nothing more than that
    }
}
