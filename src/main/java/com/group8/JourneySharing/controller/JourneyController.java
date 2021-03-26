package com.group8.JourneySharing.controller;


import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.vo.NewUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
        LOGGER.info("createjourney completed: " + newJourney.toString() );
        return new ResponseEntity<>(savedJourney, HttpStatus.CREATED);
    }

    @PutMapping(value = "/joinjourney/{journeyID}")
    public Journey joinJourney(@PathVariable String journeyID, @RequestBody String userID) throws Exception {
        LOGGER.info("joinJourney initiated...");
        Journey journey = journeyService.getJourneyByID(journeyID);
        User participant = userService.getUserByID(userID);
        journey.addParticipant(participant);
        LOGGER.info("Added user with id {} to participants of journey with id {}", participant.getUserId(), journey.getJourneyId());
        LOGGER.info("joinJourney completed...");
        return journey;
    }

}
