package com.group8.JourneySharing.vo;

import javax.validation.constraints.NotEmpty;

public class NewUserVo {

    @NotEmpty(message = "userName is a required parameter")
    private String userName;

    @NotEmpty(message = "password is a required parameter")
    private String password;

    @NotEmpty(message = "firstName is a required parameter")
    private String firstName;

    @NotEmpty(message = "lastName is a required parameter")
    private String lastName;

    @NotEmpty(message = "email is a required parameter")
    private String email;

    public NewUserVo() {
        super();
    }

    public NewUserVo(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
