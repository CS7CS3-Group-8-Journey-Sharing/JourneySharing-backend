package com.group8.JourneySharing.controller;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.RequestService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/request")
public class RequestController {

    final static Logger LOGGER = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private JourneyService journeyService;
    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping(value = "/createRequest")
    public String createRequest(@RequestParam String userEmail, @RequestParam String journeyId) throws Exception {
        LOGGER.info("Request initiated: " + userEmail.toString() + ""+ journeyId.toString());
        String savedRequestID = requestService.createRequest(userEmail,journeyId);
        LOGGER.info("Request completed ID: " + savedRequestID.toString() );
        return savedRequestID;
    }

    @GetMapping("/getRequests")
    public ResponseEntity<List<Requests>> getRequestsByEmail(@RequestParam String userEmail) {
        LOGGER.info("getRequestsByEmail initiated: " + userEmail);
        List<Requests> requestList = requestService.getRequestsByEmail(userEmail);
        LOGGER.info("getRequestsByEmail completed: " + requestList.toString());
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

}
