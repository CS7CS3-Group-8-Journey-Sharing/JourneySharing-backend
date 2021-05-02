package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/journey")
public class JourneyController {

    final static Logger LOGGER = LoggerFactory.getLogger(JourneyController.class);

    @Autowired
    private JourneyService journeyService;
    @Autowired
    private UserService userService;


    @PostMapping(value = "/createjourney")
    public ResponseEntity<Journey> createJourney(@RequestBody @Valid NewJourneyVo newJourney) throws Exception {
        LOGGER.info("createjourney initiated: " + newJourney.toString() );
        Journey savedJourney = journeyService.createJourney(newJourney);
        LOGGER.info("createjourney completed: " + savedJourney.toString() );
        return new ResponseEntity<>(savedJourney, HttpStatus.CREATED);
    }

    @PutMapping(value = "/joinjourney/")
    public Journey joinJourney(@RequestParam String journeyID, @RequestParam String userId) throws Exception {
        LOGGER.info("joinJourney initiated...");
        Journey journey = journeyService.getJourneyByID(journeyID);
        journey.addParticipant(userId);
        LOGGER.info("Added user with id {} to participants of journey with id {}", userId, journey.getJourneyId());
        LOGGER.info("joinJourney completed...");
        return journey;
    }

    @PostMapping(value = "/deleterecurring")
    public ResponseEntity<Journey> deleteRecurring(@RequestParam String journeyId) {
        LOGGER.info("deleteRecurring initiated: " + journeyId );
        Journey savedJourney = journeyService.deleteRecurring(journeyId);
        LOGGER.info("deleteRecurring completed: " + journeyId );
        return new ResponseEntity<>(savedJourney, HttpStatus.OK);
    }

    //gets the journeys for which you are the owner which are not completed or which are recurring
    @PostMapping(value = "/getjourneys")
    public ResponseEntity<List<Journey>> getJourneys(@RequestParam String userEmail) {
        LOGGER.info("getjourneys initiated: " + userEmail );
        userService.getUserByEmail(userEmail);
        List<Journey> savedJourney = journeyService.getJourneys(userEmail);
        LOGGER.info("getjourneys completed: " + userEmail );
        return new ResponseEntity<>(savedJourney, HttpStatus.OK);
    }

    //gets the journeys for which you are the participants
    @PostMapping(value = "/gethistory")
    public ResponseEntity<List<Journey>> getHistory(@RequestParam String userEmail) {
        LOGGER.info("getHistory initiated: " + userEmail );
        UserDetailsVo user = userService.getUserByEmail(userEmail);
        List<Journey> journey = journeyService.getHistory(user.getHistory());
        LOGGER.info("getHistory completed: " + userEmail );
        return new ResponseEntity<>(journey, HttpStatus.OK);
    }

    @GetMapping(value = "/getjourneysradius")
    public ResponseEntity<List<Journey>> getJourneysWithinRadius(@RequestParam double lat, @RequestParam double lng, @RequestParam int radius) throws Exception {
        LOGGER.info("get journeys initiated within: " + String.valueOf(radius) + " radius." );
        List<Journey> journeyList = journeyService.getJourneysWithinRadius(lat, lng, radius);
        LOGGER.info("get journeys completed.");
        return new ResponseEntity<>(journeyList, HttpStatus.ACCEPTED);
    }

}
