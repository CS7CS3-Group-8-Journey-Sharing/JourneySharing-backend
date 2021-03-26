package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JourneyRepository extends MongoRepository<Journey,String> {
}
