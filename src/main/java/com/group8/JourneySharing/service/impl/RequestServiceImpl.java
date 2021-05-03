package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.Gender;
import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.RequestStatus;
import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.entity.ViewStatus;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.RequestRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.RequestService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.RequestsVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private static final ModelMapper modelMapper = new ModelMapper();

    final static Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);

    private RequestRepository requestRepository;

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    private JourneyService journeyService;
    private UserService userService;

    @Autowired
    public void setJourneyService(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



// Todo : test not Written
    @Override
    public String createRequest(String userEmail, String journeyId) {
        Journey journey = journeyService.getJourneyByID(journeyId);
        UserDetailsVo user = userService.getUserByEmail(userEmail);
        if(journey.isWomanOnly() && user.getGender()!= Gender.FEMALE) {
            throw new BadRequestException("Can't accept request as its women only journey");
        }
        if(journey.getParticipantEmails().size() == journey.getMaxParticipants())
        {
            throw new BadRequestException("Can't accept request, journey has reached max number of participants");
        }
        Requests requests = new Requests(userEmail,journeyId,journey.getName());
        Requests savedRequest = requestRepository.save(requests);
        journey.getRequests().add(savedRequest.getRequestId());
        journeyService.saveJourney(journey);
        return savedRequest.getRequestId();
    }

    @Override
    public Requests getRequest(String id) {
        Optional<Requests> requestsOptional = requestRepository.findById(id);
        if(!requestsOptional.isPresent()) {
            throw new BadRequestException("Invalid request");
        }
        Requests requests = requestsOptional.get();
        return requests;
    }

//Todo : Test Not written
    @Override
    public List<RequestsVo> getRequestsByEmail(String userEmail) {
        List<Journey> journeys = journeyService.getJourneys(userEmail);
        ArrayList<String> requestedId = new ArrayList<>();
        for(Journey journey : journeys)
        {
            requestedId.addAll(journey.getRequests());
        }
        List<Requests> requests = (List<Requests>)requestRepository.findAllById(requestedId);
        List<RequestsVo> pendingRequests = new ArrayList<>();
        for (Requests request: requests){
            if(request.getRequestStatus() == RequestStatus.pending)
            {
                RequestsVo requestsVo = new RequestsVo();
                requestsVo.setRequestId(request.getRequestId());
                requestsVo.setRequestStatus(request.getRequestStatus());
                requestsVo.setJourneyId(request.getJourneyId());
                requestsVo.setJourneyName(request.getJourneyName());
                requestsVo.setViewStatus(request.getViewStatus());
                requestsVo.setRequestedUser(userService.getUserByEmail(request.getRequestedUserEmail()));
                pendingRequests.add(requestsVo);
            }
        }
        return pendingRequests;
    }

    @Override
    public void updateToSeen(List<String> requestIds) {
        for (String requestId : requestIds)
        {
            Requests requests = requestRepository.findById(requestId).get();
            requests.setViewStatus(ViewStatus.seen);
            requestRepository.save(requests);
        }
    }

    @Override
    public void denyRequest(String requestId) {
        LOGGER.info("DenyRequest" + requestId);
        Requests requests = requestRepository.findById(requestId).get();
        requests.setRequestStatus(RequestStatus.denied);
        requests.setViewStatus(ViewStatus.seen);
        requestRepository.save(requests);
    }

    @Override
    public List<Requests> getRequestsYouMade(String userEmail) {
        List<Requests>  requests = requestRepository.findByRequestedUserEmail(userEmail);
        List<Requests> pendingRequests = new ArrayList<>();
        for(Requests request : requests) {
            if(request.getRequestStatus() == RequestStatus.pending)
            {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    @Override
    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }
}
