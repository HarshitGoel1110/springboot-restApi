package com.example.restapi.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname" , "lastname"}) ---- Part of Static Filtering

// here we are specifying the id that we have used in the JacksonFilterMapping Controller
//@JsonFilter(value = "useFilter")
public class User extends RepresentationModel {
    @Id
    @GeneratedValue
    @JsonView(Views.External.class)
    private Long userId;

    @NotEmpty(message = "Username is a mandatory field, kindly provide it to proceed")
    @Column(name = "USER_NAME" , length = 50 , nullable = false , unique = true)
    @JsonView(Views.External.class)
    private String username;

    @Size(min = 2 , message = "First Name should have at least 2 characters")
    @Column(name = "FIRST_NAME" , length = 50 , nullable = false)
    @JsonView(Views.External.class)
    private String firstname;

    @Column(name = "LAST_NAME" , length = 50 , nullable = false)
    @JsonView(Views.External.class)
    private String lastname;

    @Column(name = "EMAIL_ADDRESS" , length = 50 , nullable = false)
    @JsonView(Views.External.class)
    private String email;

    @Column(name = "ROLE" , length = 50 , nullable = false)
    @JsonView(Views.Internal.class)
    private String role;

    @Column(name = "SSN" , length = 50 , nullable = false , unique = true)
//    @JsonIgnore ---- Part of Static Filtering
    @JsonView(Views.Internal.class)
    private String ssn;

    // it means one user can place many orders
    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;

    @Column(name = "ADDRESS")
    private String address;

    public User() {
    }

    public User(Long userId, String username, String firstname, String lastname, String email, String role, String ssn, List<Order> orders, String address) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.orders = orders;
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                ", address='" + address + '\'' +
                '}';
    }
}
/*
insert into user values(101 , "abc@gmail.com" , "ABC" , "Goel" , "SDE" , "ssn101" , "abc");
insert into user values(102 , "def@gmail.com" , "DEF" , "Goel" , "Intern" , "ssn102" , "def");
insert into user values(103 , "ghi@gmail.com" , "GHI" , "Goel" , "Admin" , "ssn103" , "ghi");

insert into user(ID , EMAIL_ADDRESS , FIRST_NAME , LAST_NAME , ROLE , SSN , USER_NAME)
values(101 , "abc@gmail.com" , "ABC" , "Goel" , "SDE" , "ssn101" , "abc");

 */