package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.repository.UserRepository;
import com.group8.JourneySharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
