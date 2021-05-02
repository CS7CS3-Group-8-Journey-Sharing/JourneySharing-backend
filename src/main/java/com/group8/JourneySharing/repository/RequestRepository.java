package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.RequestStatus;
import com.group8.JourneySharing.entity.Requests;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface RequestRepository extends MongoRepository<Requests,String> {

    void deleteByJourneyId(String journeyId);

    List<Requests> findByRequestedUserEmail(String userEmail);
}
