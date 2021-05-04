package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.EditUserVo;
import com.group8.JourneySharing.vo.PaymentVo;
import com.group8.JourneySharing.vo.RatingVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/addtohistory")
    public ResponseEntity<User> addToHistory(@RequestParam String userEmail, @RequestParam String journeyId) {
        LOGGER.info("addToHistory initiated: " + journeyId );
        User savedUser = userService.addToHistory(userEmail,journeyId);
        LOGGER.info("addToHistory completed: " + journeyId );
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping(value = "/addrating")
    public ResponseEntity<Void> addRating(@RequestBody List<RatingVo> ratings) {
        LOGGER.info("addRating initiated");
        userService.addRating(ratings);
        LOGGER.info("addRating completed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getRating")
    public ResponseEntity<Double> getRating(@RequestParam String userEmail) {
        LOGGER.info("getRating initiated: " + userEmail);
        double rating = userService.getRating(userEmail);
        LOGGER.info("getRating completed: " + userEmail);
        return new ResponseEntity<>(rating,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteUser (@RequestParam String userEmail) {
        LOGGER.info("deleteUser initiated: " + userEmail);
        userService.deleteUser(userEmail);
        LOGGER.info("deleteUser completed: " + userEmail);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/editUser")
    public ResponseEntity<Void> editUser (@RequestParam String userEmail, @RequestBody EditUserVo user) {
        LOGGER.info("editUser initiated: " + userEmail);
        userService.editUser(userEmail, user);
        LOGGER.info("editUser completed: " + userEmail);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping (value = "/getpaymentdetails")
    public ResponseEntity<PaymentVo> getPaymentDetails (@RequestParam String journeyId)
    {
        LOGGER.info("getPaymentDetails initiated: " + journeyId);
        PaymentVo paymentVo = userService.getPaymentDetails(journeyId);
        LOGGER.info("getPaymentDetails completed: " + journeyId);
        return new ResponseEntity<>(paymentVo, HttpStatus.OK);
    }
}
