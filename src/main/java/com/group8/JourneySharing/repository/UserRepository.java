package com.group8.JourneySharing.repository;

import com.group8.JourneySharing.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String username);

    public User findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);
}
