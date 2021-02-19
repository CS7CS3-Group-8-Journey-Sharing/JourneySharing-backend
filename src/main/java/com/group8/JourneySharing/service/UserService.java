package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.User;

import java.util.ArrayList;

public interface UserService {

    public User addUser(User user);
    public User getUserByUsername(String username);
    public ArrayList<User> getAllUsers();

}
