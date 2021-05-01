package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Journey> joinJourney(@RequestParam String journeyID, @RequestParam String userId) throws Exception {
        LOGGER.info("joinJourney initiated...");
        Journey journey = journeyService.getJourneyByID(journeyID);
        journey.addParticipant(userId);
        Journey savedJourney = journeyService.saveJourney(journey);
        LOGGER.info("Added user with id {} to participants of journey with id {}", userId, journey.getJourneyId());
        LOGGER.info("joinJourney completed...");
        return new ResponseEntity<>(savedJourney, HttpStatus.ACCEPTED);
    }
}
