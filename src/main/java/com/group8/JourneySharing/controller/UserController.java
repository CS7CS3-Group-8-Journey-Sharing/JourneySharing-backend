package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public ResponseEntity<UserDetailsVo> getUserByEmail(@RequestParam String email) throws Exception {
        LOGGER.info("Get User initiated: " + email);
        UserDetailsVo user = userService.getUserByEmail(email);
        LOGGER.info("Get User completed: " + user.toString());
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
