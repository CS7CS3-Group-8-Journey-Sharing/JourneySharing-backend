package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JourneyRepository extends MongoRepository<Journey,String> {

    List<Journey> findByOwnerEmailAndRecurring (String userId,boolean recurring);

    List<Journey> findByOwnerEmailAndRecurringAndCompleted (String userId,boolean recurring, boolean completed);
}
