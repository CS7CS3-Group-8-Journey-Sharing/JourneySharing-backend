package com.group8.JourneySharing.service;


import com.group8.JourneySharing.entity.RequestStatus;
import com.group8.JourneySharing.entity.Requests;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RequestService {


    String createRequest(String email, String journeyid);

    RequestStatus getRequest(String id);

    List<Requests> getRequestsByEmail(String userEmail);

}
