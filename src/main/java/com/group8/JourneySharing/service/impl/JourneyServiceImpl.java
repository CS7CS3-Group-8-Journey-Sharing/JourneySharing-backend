package com.group8.JourneySharing.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Location;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyServiceImpl implements JourneyService {

    private static final ModelMapper modelMapper = new ModelMapper();

    final static Logger LOGGER = LoggerFactory.getLogger(JourneyServiceImpl.class);

    private JourneyRepository journeyRepository;

    @Autowired
    public void setJourneyRepository(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @Autowired
    private UserService userService;

    public JourneyServiceImpl() {
        modelMapper.typeMap(NewJourneyVo.class, Journey.class).addMappings(mapper -> {
            mapper.skip(Journey::setJourneyId); // mapper sets the JourneyId with the ownerId!?!
        });
    }

    @Override
    public Journey createJourney(NewJourneyVo newJourney) {
        Journey journey = modelMapper.map(newJourney, Journey.class);
        Journey savedJourney = journeyRepository.save(journey);
        LOGGER.info("Journey with id {} created", savedJourney.getJourneyId());
        return savedJourney;
    }

    @Override
    public Journey getJourneyByID(String journeyId) {
        Optional<Journey> journeyOptional = journeyRepository.findById(journeyId.toLowerCase());
        if (!journeyOptional.isPresent()) {
            LOGGER.error("Invalid journeyID");
            throw new BadRequestException("Invalid journeyID");
        }
        Journey journey = journeyOptional.get();
        LOGGER.info("Journey Fetched Successfully {}", journey);
        return journey;
    }

    @Override
    public Journey deleteRecurring(String journeyId) {
        Optional<Journey> journeyOptional = journeyRepository.findById(journeyId.toLowerCase());
        if (!journeyOptional.isPresent()) {
            LOGGER.error("Invalid journeyID");
            throw new BadRequestException("Invalid journeyID");
        }
        Journey journey = journeyOptional.get();
        journey.setRecurring(false);
        journey.setCompleted(true);
        journey = journeyRepository.save(journey);
        return journey;
    }

    @Override
    public List<Journey> getJourneys(String userEmail) {
        List<Journey> journeys = journeyRepository.findByOwnerEmailAndRecurring(userEmail,true);
        List<Journey> notCompletedJourneys = journeyRepository.findByOwnerEmailAndRecurringAndCompleted(userEmail,false,false);
        if(journeys == null)
        {
            journeys = new ArrayList<Journey>();
        }
        journeys.addAll(notCompletedJourneys);
        return journeys;
    }

    @Override
    public List<Journey> getHistory(List<String> journeys) {
        return (List<Journey>) journeyRepository.findAllById(journeys);
    }

    @Override
    public List<Journey> getJourneysWithinRadius(double userLat, double userLng, int radius) {
        List<Journey> allJourneys = journeyRepository.findAll();
        List<Journey> journeysWithinRadius = new LinkedList<Journey>();

        for(Journey journey: allJourneys){
            Location journeyStartLocation = journey.getStartLocation();
            double latJourney = journeyStartLocation.getLat();
            double lngJourney = journeyStartLocation.getLng();

            double dist = journeyStartLocation.meterDistanceBetweenPoints(userLat, userLng, latJourney, lngJourney);

            if (dist<radius){
                journeysWithinRadius.add(journey);
            }
        }

        return journeysWithinRadius;
    }
}
