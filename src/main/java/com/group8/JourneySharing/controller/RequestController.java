package com.group8.JourneySharing.controller;

import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.RequestService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.RequestsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<RequestsVo>> getRequestsByEmail(@RequestParam String userEmail) {
        LOGGER.info("getRequestsByEmail initiated: " + userEmail);
        List<RequestsVo> requestList = requestService.getRequestsByEmail(userEmail);
        LOGGER.info("getRequestsByEmail completed: " + requestList.toString());
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @PostMapping(value = "/updatetoseen")
    public ResponseEntity<Void> updateToSeen(@RequestBody List<String> requestIds) {
        LOGGER.info("updateToSeen initiated");
        requestService.updateToSeen(requestIds);
        LOGGER.info("updateToSeen completed");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/denyrequest")
    public ResponseEntity<Void> denyRequest(@RequestParam String requestId) {
        LOGGER.info("denyRequest initiated");
        requestService.denyRequest(requestId);
        LOGGER.info("denyRequest completed");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/getyourrequests")
    public ResponseEntity<List<Requests>> getRequestsYouMade(@RequestParam String userEmail) {
        LOGGER.info("getRequestsYouMade initiated: " + userEmail);
        List<Requests> requestList = requestService.getRequestsYouMade(userEmail);
        LOGGER.info("getRequestsYouMade completed: " + userEmail);
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @PostMapping(value = "/deleterequest")
    public ResponseEntity<Void> deleteRequest(@RequestParam String requestId) {
        LOGGER.info("deleteRequest initiated: " + requestId);
        requestService.deleteRequest(requestId);
        LOGGER.info("deleteRequest completed: " + requestId);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }
}
