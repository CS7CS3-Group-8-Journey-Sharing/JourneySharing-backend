package com.group8.JourneySharing.service.impl;

import com.group8.JourneySharing.controller.UserController;
import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.repository.UserRepository;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private static final ModelMapper modelMapper = new ModelMapper();

    final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(NewUserVo newUser) {
        User user = modelMapper.map(newUser, User.class);
        user.setHistory(new ArrayList<Journey>());
        User savedUser = userRepository.save(user);
        return savedUser.getUserName();
    }

    @Override
    public User getUserByUsername(String username) {
        User savedUser = userRepository.findByUserName(username);
        return savedUser;
    }

    @Override
    public ArrayList getAllUsers() {
        ArrayList<User> savedUser = (ArrayList)userRepository.findAll();
        return savedUser;
    }
}
