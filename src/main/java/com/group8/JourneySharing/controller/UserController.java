package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping(value = "/adduser")
    public ResponseEntity<String> addUser( @RequestBody @Valid NewUserVo newUser) throws Exception {
        LOGGER.info("AddUser initiated: " + newUser.toString() );
        String userName = this.userService.addUser(newUser);
        LOGGER.info("AddUser completed: " + newUser.toString() );
        return new ResponseEntity<>(userName, HttpStatus.CREATED);
    }



}
