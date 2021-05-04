package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    void deleteByEmail(String userEmail);
}
