package com.group8.JourneySharing.service.impl;


import com.group8.JourneySharing.entity.*;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.RequestRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewJourneyVo;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.RequestsVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith( MockitoJUnitRunner.class)
public class RequestServiceImplTest {

    final static Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);
    private static final ModelMapper modelMapper = new ModelMapper();

    private RequestServiceImpl requestServiceImpl;
    private JourneyServiceImpl journeyServiceImpl;
    private UserServiceImpl userServiceImpl;

    private RequestsVo requestsVo;
    private User user;
    private Requests requests;
    private NewJourneyVo newjourney;
    private Journey journey;
    private NewUserVo newUser;
    private UserDetailsVo userDetails;
    private Location location;

    @Mock
    JourneyService journeyService;

    @Mock
    RequestRepository requestRepository;

    @Mock
    UserService userService;




    @Before
    public void init() {



        requestServiceImpl = new RequestServiceImpl();
        requestServiceImpl.setRequestRepository(requestRepository);
        requestServiceImpl.setJourneyService(journeyService);
        requestServiceImpl.setUserService(userService);
        journey = new Journey("journeyId", "name", false, false, "ownerEmail", null,
                null, null, 6, null, null, null, null, null,
                false, false, 0);
        //journey = new Journey("journeyId","name","recurring",false,"owneremail",participantemails,null,null,10,null,null,null,null,false,false,22.22);
        newjourney = new NewJourneyVo();
        user = new User("email", "Unique char","password","firstName","lastName","mobile number","iban", new ArrayList<String>(),20, Gender.FEMALE, new Rating(10.0,2));
        newUser = new NewUserVo("email", "password","firstName","lastName");
        userDetails = new UserDetailsVo("email", "firstName","lastName", "mobileNumber","iban", new ArrayList<String>(),20, Gender.FEMALE, new Rating(10.0,2));
        requestsVo = new RequestsVo("requestId", userDetails,"journeyId",RequestStatus.pending,ViewStatus.unseen);
        requests = new Requests("requestId","requestedUserEmail","journeyId",RequestStatus.pending,ViewStatus.unseen);

    }

    @Test (expected = BadRequestException.class)
    public void testForMaleRequets()
    {
        userDetails.setGender(Gender.MALE);
        journey.setWomanOnly(true);
        given(journeyService.getJourneyByID(ArgumentMatchers.anyString())).willReturn(journey);
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        requestServiceImpl.createRequest("userEmail","journeyId");

    }
    @Test (expected = BadRequestException.class)
    public void testForMaxParticipantsinJourney()
    {
        userDetails.setGender(Gender.FEMALE);
        journey.setWomanOnly(true);
        journey.setMaxParticipants(2);
        List<String> emails_count = new ArrayList<>();
        emails_count.add("abc@gmail.com");
        emails_count.add("def@gmail.com");
        journey.setParticipantEmails((ArrayList<String>) emails_count);
        given(journeyService.getJourneyByID(ArgumentMatchers.anyString())).willReturn(journey);
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        requestServiceImpl.createRequest("userEmail","journeyId");

    }
    @Test
    public void testForCreateRequest()
    {
        userDetails.setGender(Gender.FEMALE);
        journey.setWomanOnly(true);
        journey.setMaxParticipants(3);
        List<String> emails_count = new ArrayList<>();
        emails_count.add("abc@gmail.com");
        emails_count.add("def@gmail.com");
        journey.setParticipantEmails((ArrayList<String>) emails_count);
        given(journeyService.getJourneyByID(ArgumentMatchers.anyString())).willReturn(journey);
        given(userService.getUserByEmail(ArgumentMatchers.anyString())).willReturn(userDetails);
        given(requestRepository.save(ArgumentMatchers.any(Requests.class))).willReturn(requests);
        journey.setRequests((ArrayList<String>) emails_count);
        given(journeyService.saveJourney(ArgumentMatchers.any(Journey.class))).willReturn(null);
        requestServiceImpl.createRequest("userEmail","journeyId");

    }
    @Test
    public void testForUpdatingSeen()
    {
        List<String> requestIds = new ArrayList<>();
        requestIds.add("01");
        requestIds.add("02");
        requests.setViewStatus(ViewStatus.seen);
        given(requestRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.ofNullable(requests));
        given(requestRepository.save(ArgumentMatchers.any(Requests.class))).willReturn(null);
        requestServiceImpl.updateToSeen(requestIds);
    }
    @Test
    public void testForDenyRequest()
    {
        requests.setRequestStatus(RequestStatus.denied);
        requests.setViewStatus(ViewStatus.seen);
        given(requestRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.ofNullable(requests));
        given(requestRepository.save(ArgumentMatchers.any(Requests.class))).willReturn(null);
        requestServiceImpl.denyRequest("requestId");
    }
    @Test
    public void testForGetRequestsYouMade()
    {
        requests.setRequestStatus(RequestStatus.pending);

        given(requestRepository.findByRequestedUserEmail(ArgumentMatchers.anyString())).willReturn((new ArrayList<Requests> ()));
        given(requestRepository.save(ArgumentMatchers.any(Requests.class))).willReturn(null);
        requestServiceImpl.getRequestsYouMade("userEmail");
    }

    //doNothing().when(userRepository).deleteByEmail(ArgumentMatchers.anyString());
    @Test
    public void testForDeleteRequest()
    {

        doNothing().when(requestRepository).deleteById(ArgumentMatchers.anyString());
        requestServiceImpl.deleteRequest("requestId");
    }

    @Test
    public void testForGetRequestsByEmail()
    {
        requests.setRequestStatus(RequestStatus.pending);
        given(journeyService.getJourneys(ArgumentMatchers.anyString())).willReturn((new ArrayList<Journey> ()));
        given(requestRepository.findAllById(ArgumentMatchers.anyList())).willReturn((new ArrayList<Requests> ()));
        requestServiceImpl.getRequestsByEmail("userEmail");
    }



}
