package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.vo.EditUserVo;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.PaymentVo;
import com.group8.JourneySharing.vo.RatingVo;
import com.group8.JourneySharing.vo.UserDetailsVo;

import java.util.List;


public interface UserService {

    public String addUser(NewUserVo newUser);

    public UserDetailsVo getUserByEmail(String email);

    public User getUserByID(String id);

    User addToHistory(String userEmail, String journeyId);

    void addRating(List<RatingVo> ratings);

    double getRating(String userEmail);

    void deleteUser(String userEmail);

    void editUser(String userEmail, EditUserVo user);

    PaymentVo getPaymentDetails(String journeyId);
}
