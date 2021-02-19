package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.repository.UserRepository;
import com.group8.JourneySharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User getUserByUsername(String username) {
        User savedUser = userRepository.findByUsername(username);
        return savedUser;
    }

    @Override
    public ArrayList getAllUsers() {
        ArrayList<User> savedUser = (ArrayList)userRepository.findAll();
        return savedUser;
    }
}
