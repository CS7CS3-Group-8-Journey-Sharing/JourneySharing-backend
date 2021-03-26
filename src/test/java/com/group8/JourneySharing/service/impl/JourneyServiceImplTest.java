package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.BDDMockito.given;

@RunWith( MockitoJUnitRunner.class)
public class JourneyServiceImplTest {

    final static Logger LOGGER = LoggerFactory.getLogger(JourneyServiceImpl.class);


    private JourneyServiceImpl journeyServiceImpl;

    private Journey journey;
    private NewJourneyVo newjourney;

    @Mock
    private JourneyRepository journeyRepository;
    @Before
    public void setUp() {
        journeyServiceImpl = new JourneyServiceImpl();
        journeyServiceImpl.setJourneyRepository(journeyRepository);
        journey = new Journey();
        newjourney = new NewJourneyVo();

    }

    @Test
    public void testForCreteJourney(){
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        journeyServiceImpl.createJourney(newjourney);

    }

}
