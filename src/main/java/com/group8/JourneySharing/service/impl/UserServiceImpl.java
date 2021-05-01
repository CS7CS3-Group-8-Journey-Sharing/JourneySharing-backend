package com.group8.JourneySharing.service.impl;


import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.repository.UserRepository;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final ModelMapper modelMapper = new ModelMapper();

    final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setUserRepository( UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Autowired
    private JourneyRepository journeyRepository;

//    @Autowired
//    public void setJourneyRepository(JourneyRepository journeyRepository) {
//        this.journeyRepository = journeyRepository;
//    }

    @Override
    public String addUser(NewUserVo newUser) {
    	String userEmail = newUser.getEmail().toLowerCase();
    	if(userRepository.findByEmail(userEmail) != null) {
    	    LOGGER.error("Email Already Exists");
    		throw new BadRequestException("Email Already Exists");
    	}
        User user = modelMapper.map(newUser, User.class);
        user.setEmail(userEmail);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setHistory(new ArrayList<String>());
        User savedUser = userRepository.save(user);
        LOGGER.info("User saved with id {}", savedUser.getUserId());
        return savedUser.getEmail();
    }

    @Override
    public UserDetailsVo getUserByEmail(String email) {
        User savedUser = userRepository.findByEmail(email.toLowerCase());
        if(savedUser == null){
            LOGGER.error("Email "+email+ " not found.");
            throw new BadRequestException("Email "+email+ " not found.");
        }

        UserDetailsVo user = modelMapper.map(savedUser, UserDetailsVo.class);
        LOGGER.info("User Details Fetched Successfully {}", user);
        return user;
    }

    @Override
    public User getUserByID(String id) {
        Optional<User> userOptional = userRepository.findById(id.toLowerCase());
        if(!userOptional.isPresent()) {
            LOGGER.error("Invalid userId");
            throw new BadRequestException("Invalid userId");
        }
        User user = userOptional.get();
        LOGGER.info("User Fetched Successfully {}", user);
        return user;
    }

    @Override
    public User addToHistory(String userEmail, String journeyId) {
        Optional<Journey> journeyOptional = journeyRepository.findById(journeyId.toLowerCase());
        if (!journeyOptional.isPresent()) {
            LOGGER.error("Invalid journeyID");
            throw new BadRequestException("Invalid journeyID");
        }
        Journey journey = journeyOptional.get();
        User user = userRepository.findByEmail(userEmail.toLowerCase());
        if(user == null) {
            LOGGER.error("Invalid userEmail");
            throw new BadRequestException("Invalid userEmail");
        }
        user.addHistory(journey.getJourneyId());
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
