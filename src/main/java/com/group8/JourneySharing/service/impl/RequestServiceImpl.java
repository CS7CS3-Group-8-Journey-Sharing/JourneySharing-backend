package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.RequestStatus;
import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.repository.RequestRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.RequestService;
import com.group8.JourneySharing.service.UserService;
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

    @Autowired
    private JourneyService journeySerive;

    @Autowired
    private UserService userService;

    @Override
    public String createRequest(String userEmail, String journeyId) {
        //String journeyId = requests.getJourneydId().toLowerCase();
        //get journey by id
        // keep it here
        Journey journey = journeySerive.getJourneyByID(journeyId);
        Requests requets = new Requests(userEmail,journeyId);
        Requests savedRequest = requestRepository.save(requets);
        // request id
        // savedjourney journeyserive impl
        journey.getRequests().add(savedRequest.getRequestId());
        Journey updatedjourney= journeySerive.saveJourney(journey);
        LOGGER.info("User saved with id {}", savedRequest.getRequestId());
        LOGGER.info("User saved with id {}", updatedjourney.getJourneyId());
        return savedRequest.getRequestId();
    }
    public RequestStatus getRequest(String id) {
        Optional<Requests> savedRequest = requestRepository.findById(id);
        Requests req = savedRequest.get();
        return req.getRequestStatus();
    }


    @Override
    public List<Requests> getRequestsByEmail(String userEmail) {
        List<Journey> journeys = journeySerive.getJourneys(userEmail);
        ArrayList<String> requestedId = new ArrayList<>();
        for(Journey journey : journeys)
        {
            requestedId.addAll(journey.getRequests());
        }
        List<Requests> requests = (List<Requests>)requestRepository.findAllById(requestedId);
        List<Requests> pendingRequests = new ArrayList<>();
        for (Requests request: requests){
            if(request.getRequestStatus() == RequestStatus.pending)
            {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }
}
