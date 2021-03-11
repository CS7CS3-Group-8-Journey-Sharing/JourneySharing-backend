package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.vo.NewJourneyVo;
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

    @Override
    public Journey createJourney(NewJourneyVo newJourney) {
        Journey journey = modelMapper.map(newJourney, Journey.class);
        Journey savedJourney  = journeyRepository.save(journey);
        LOGGER.info("Journey with id {} created", savedJourney.getJourneyId());
        return savedJourney;


    }
}
