package com.group8.JourneySharing.service;


import com.group8.JourneySharing.entity.Requests;
import com.group8.JourneySharing.vo.RequestsVo;

import java.util.List;

public interface RequestService {


    String createRequest(String email, String journeyid);

    Requests getRequest(String id);

    List<RequestsVo> getRequestsByEmail(String userEmail);

    void updateToSeen(List<String> requestIds);

    void denyRequest(String requestId);

    List<Requests> getRequestsYouMade(String userEmail);

    void deleteRequest(String requestId);
}
