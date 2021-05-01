package com.group8.JourneySharing.service.impl;

import java.util.Optional;

import com.group8.JourneySharing.entity.Journey;
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
    public Journey saveJourney(Journey newJourney) {
        Journey savedJourney = journeyRepository.save(newJourney);
        LOGGER.info("Journey with id {} saved", savedJourney.getJourneyId());
        return savedJourney;
    }

    @Override
    public Journey getJourneyByID(String journeyID) {
        Optional<Journey> journeyOptional = journeyRepository.findById(journeyID.toLowerCase());
        if (!journeyOptional.isPresent()) {
            LOGGER.error("Invalid journeyID");
            throw new BadRequestException("Invalid journeyID");
        }
        Journey journey = journeyOptional.get();
        LOGGER.info("Journey Fetched Successfully {}", journey);
        return journey;
    }
}
