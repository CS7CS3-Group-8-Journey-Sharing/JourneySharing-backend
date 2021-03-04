package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.vo.NewUserVo;

import java.util.ArrayList;

public interface UserService {

    public String addUser(NewUserVo newUser);
    public User getUserByUsername(String username);
    public ArrayList<User> getAllUsers();

}
