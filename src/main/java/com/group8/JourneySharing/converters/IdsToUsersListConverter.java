package com.group8.JourneySharing.converters;

import com.group8.JourneySharing.entity.User;
import com.group8.JourneySharing.service.UserService;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class IdsToUsersListConverter extends AbstractConverter<ArrayList<String>, ArrayList<User>> {

    @Autowired
    private UserService userService;

    @Override
    protected ArrayList<User> convert(ArrayList<String> ids) {

        return ids
                .stream()
                .map(src -> userService.getUserByID(src))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}