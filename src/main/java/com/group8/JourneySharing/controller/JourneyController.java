package com.group8.JourneySharing.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/journey")
public class JourneyController {

    @GetMapping(value = "/hi")
    public ResponseEntity<String> hi() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
