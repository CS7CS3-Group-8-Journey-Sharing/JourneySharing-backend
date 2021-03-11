package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.User;


import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {
	
	
	
    public User findByUserName(String username);
    public User findByEmail(String email);
    
}
