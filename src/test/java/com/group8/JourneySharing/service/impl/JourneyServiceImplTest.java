package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.Gender;
import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Rating;
import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.repository.RequestRepository;
import com.group8.JourneySharing.service.RequestService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Optional;

@RunWith( MockitoJUnitRunner.class)
public class JourneyServiceImplTest {

    final static Logger LOGGER = LoggerFactory.getLogger(JourneyServiceImpl.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    private JourneyServiceImpl journeyServiceImpl;

    private NewJourneyVo newJourney;
    private User user;
    private UserDetailsVo userDetails;
    private Journey journey;
    private Requests requests;

    @Mock
    private JourneyRepository journeyRepository;

    @Mock
    UserService userService;

    @Mock
    RequestRepository requestRepository;

    @Mock
    RequestService requestService;

    @Before
    public void setUp() {
        journeyServiceImpl = new JourneyServiceImpl();
        journeyServiceImpl.setJourneyRepository(journeyRepository);
        journeyServiceImpl.setUserService(userService);
        journeyServiceImpl.setRequestService(requestService);
        journeyServiceImpl.setRequestRepository(requestRepository);
        newJourney = new NewJourneyVo("name", false , null, "ownerEmail",
                null, null, 6, null,
                null, null, null,true , 0.0);
        user = new User("email", "Unique char","password","firstName","lastName","mobile number","iban", new ArrayList<String>(),20, Gender.FEMALE, new Rating(10.0,2));
        userDetails = new UserDetailsVo("email", "firstName","lastName", "mobileNumber","iban", null);
        journey = new Journey("journeyId", "name", false, false, "ownerEmail", new ArrayList<>(),
                null, null, 6, null, null, null, null, null,
                false, false, 0);
        requests = new Requests("userEmail", "journeyId","journeyName");
    }

    @Test
    public void testForCreateJourney(){
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        journeyServiceImpl.createJourney(newJourney);
    }

    @Test (expected = BadRequestException.class)
    public void testGetJourneyByIdInvalidJourney() {
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(null) );
        journeyServiceImpl.getJourneyByID("journeyId");
    }

    @Test
    public void testGetJourneyById() {
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        journeyServiceImpl.getJourneyByID("journeyId");
    }

    @Test (expected = BadRequestException.class)
    public void testDeleteRecurringInvalidJourney() {
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(null) );
        journeyServiceImpl.deleteRecurring("journeyId");
    }

    @Test
    public void testDeleteRecurring() {
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        journeyServiceImpl.deleteRecurring("journeyId");
    }

    @Test
    public void testGetJourneys() {
        given( journeyRepository.findByOwnerEmailAndRecurring(ArgumentMatchers.anyString(),ArgumentMatchers.anyBoolean()))
                .willReturn( new ArrayList<Journey>() );
        given( journeyRepository.findByOwnerEmailAndRecurringAndCompleted(ArgumentMatchers.anyString(),ArgumentMatchers.anyBoolean(),ArgumentMatchers.anyBoolean()))
                .willReturn( new ArrayList<Journey>() );
        journeyServiceImpl.getJourneys("journeyId");
    }

    @Test
    public void testGetHistory() {
        given( journeyRepository.findAllById(ArgumentMatchers.anyCollection())).willReturn( new ArrayList<Journey>() );
        journeyServiceImpl.getHistory(new ArrayList<String>());
    }

    @Test
    public void testGetJourneyWithinRadius() {
        given( journeyRepository.findAll()).willReturn( new ArrayList<Journey>());
        journeyServiceImpl.getJourneysWithinRadius(0,0,0);
    }

    @Test
    public void testSaveJourney() {
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        journeyServiceImpl.saveJourney(journey);
    }

    @Test
    public void testJoinJourney(){
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        given( requestService.getRequest(ArgumentMatchers.anyString())).willReturn( requests );
        given(userService.addToHistory(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).willReturn(user);
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        given(requestRepository.save(ArgumentMatchers.any(Requests.class))).willReturn(requests);
        journeyServiceImpl.joinJourney("requestId");
    }

    @Test (expected = BadRequestException.class)
    public void testJoinJourneyMaxParticipants() {
        journey.setMaxParticipants(0);
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        given( requestService.getRequest(ArgumentMatchers.anyString())).willReturn( requests );
        journeyServiceImpl.joinJourney("requestId");
    }

    @Test(expected = BadRequestException.class)
    public void testStartJourneyInvalidOwner() {
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        journeyServiceImpl.startJourney("userEmail","journeyId");
    }

    @Test
    public void testStartJourney() {
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        journeyServiceImpl.startJourney("ownerEmail","journeyId");

    }

    @Test(expected = BadRequestException.class)
    public void testEndJourneyInvalidOwner() {
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        journeyServiceImpl.endJourney("userEmail","journeyId");
    }

    @Test
    public void testEndJourney() {
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        given( journeyRepository.findById(ArgumentMatchers.anyString())).willReturn( Optional.ofNullable(journey) );
        given(journeyRepository.save(ArgumentMatchers.any(Journey.class))).willReturn(journey);
        journeyServiceImpl.endJourney("ownerEmail","journeyId");
    }

    @Test
    public void testDeleteJourney() {
        doNothing().when(requestRepository).deleteByJourneyId(ArgumentMatchers.anyString());
        doNothing().when(journeyRepository).deleteById(ArgumentMatchers.anyString());
        journeyServiceImpl.deleteJourney("journeyId");
    }

}
