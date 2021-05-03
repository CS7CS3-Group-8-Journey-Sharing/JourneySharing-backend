package com.group8.JourneySharing.service.impl;


import com.group8.JourneySharing.entity.Gender;
import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.entity.Rating;
import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.exception.BadRequestException;
import com.group8.JourneySharing.repository.JourneyRepository;
import com.group8.JourneySharing.repository.UserRepository;
import com.group8.JourneySharing.service.JourneyService;
import com.group8.JourneySharing.service.UserService;
import com.group8.JourneySharing.vo.EditUserVo;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.PaymentVo;
import com.group8.JourneySharing.vo.RatingVo;
import com.group8.JourneySharing.vo.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
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

    private JourneyRepository journeyRepository;

    @Autowired
    public void setJourneyRepository(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    @Autowired
    private JourneyService journeyService;

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

    @Override
    public void addRating(List<RatingVo> ratings) {
        for (RatingVo rating : ratings)
        {
            User user = userRepository.findByEmail(rating.getUserEmail().toLowerCase());
            if(user == null)
                continue;
            user.getRating().addRating(rating.getRating());
            userRepository.save(user);
        }
    }

    @Override
    public double getRating(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if(user == null)
        {
            throw new BadRequestException("User does not exist");
        }
        Rating userRating = user.getRating();
        double rating =  userRating.getTotalRating()/userRating.getNumOfRating();
        return  rating;
    }

    @Override
    public void deleteUser(String userEmail) {
        userRepository.deleteByEmail(userEmail);
    }

    @Override
    public void editUser(String userEmail, EditUserVo userVo) {

        User user = userRepository.findByEmail(userEmail.toLowerCase());
        if(user == null){
            throw new BadRequestException("Email "+userEmail+ " not found.");
        }
        if(userVo.getPassword() != null)
        {
            user.setPassword(passwordEncoder.encode(userVo.getPassword()));
        }
        if(userVo.getAge() != null)
        {
            user.setAge(userVo.getAge());
        }
        if(userVo.getGender() != null)
        {
            if(user.getGender() == null || user.getGender() == Gender.NONE) {
                user.setGender(userVo.getGender());
            }else {
                LOGGER.info("Can change gender from one to another");
            }
        }
        if(userVo.getIban() != null)
        {
            user.setIban(userVo.getIban());
        }
        if(userVo.getMobileNumber() != null)
        {
            user.setMobileNumber(userVo.getMobileNumber());
        }

        userRepository.save(user);
    }

    @Override
    public PaymentVo getPaymentDetails(String journeyId) {
        Journey journey = journeyService.getJourneyByID(journeyId);
        UserDetailsVo user = getUserByEmail(journey.getOwnerEmail());
        if(user.getIban() == null && user.getMobileNumber() == null)
        {
            throw new BadRequestException("User has not set-up payment details");
        }
        PaymentVo paymentVo = new PaymentVo(journey.getOwnerEmail(), user.getIban(),
                user.getMobileNumber(), journey.getPrice(),journey.getParticipantEmails());
        return paymentVo;
    }
}
