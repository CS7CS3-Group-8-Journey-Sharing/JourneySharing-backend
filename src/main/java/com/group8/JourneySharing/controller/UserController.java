package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")
    public ResponseEntity<User> addUser(@RequestBody String username) {
        return new ResponseEntity<>(userService.addUser(new User(username)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/getuser")
    public ResponseEntity<User> getUserByUsername(String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value = "/getallusers")
    public ResponseEntity<ArrayList> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
