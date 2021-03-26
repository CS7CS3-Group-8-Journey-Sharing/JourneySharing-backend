package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.vo.NewUserVo;
import com.group8.JourneySharing.vo.UserDetailsVo;


public interface UserService {

    public String addUser(NewUserVo newUser);
    public UserDetailsVo getUserByEmail(String email);
    public User getUserByID(String id);

}
