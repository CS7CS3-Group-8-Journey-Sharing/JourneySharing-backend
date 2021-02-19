package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    public String id;
    public String username;
    public String firstName;
    public String lastName;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, username=%s, firstName='%s', lastName='%s']",
                id, username,  firstName, lastName);
    }

}
