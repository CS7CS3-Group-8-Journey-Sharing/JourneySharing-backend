package com.group8.JourneySharing.service.impl;

import java.util.Optional;
import java.util.ArrayList;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.exception.BadRequestException;
import org.modelmapper.Converter;
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

    @Override
    public Journey createJourney(NewJourneyVo newJourney) {
        Converter<String, User> idToUser = ctx -> ctx.getSource() == null ? null : userService.getUserByID(ctx.getSource());
        modelMapper.typeMap(NewJourneyVo.class, Journey.class).addMappings(mapper -> {
            mapper.using(idToUser).map(NewJourneyVo::getOwnerId, Journey::setOwner);
            mapper.skip(Journey::setJourneyId); // It was setting the JourneyID with ownerID? Might be bigger problem
            // maps empty ArrayList to Journey ArrayList<Users> participants and then I add them separately below
            mapper.map(src -> new ArrayList<User>(), Journey::setParticipants);
        });

        Journey journey = modelMapper.map(newJourney, Journey.class);
        for (String id : newJourney.getParticipantIds()) {
            journey.addParticipant(userService.getUserByID(id));
        }
        Journey savedJourney = journeyRepository.save(journey);
        LOGGER.info("Journey with id {} created", savedJourney.getJourneyId());
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
