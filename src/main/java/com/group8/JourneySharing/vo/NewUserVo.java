package com.group8.JourneySharing.vo;

import javax.validation.constraints.NotEmpty;

public class NewUserVo {
    @NotEmpty(message = "email is a required parameter")
    private String email;

    @NotEmpty(message = "password is a required parameter")
    private String password;

    @NotEmpty(message = "firstName is a required parameter")
    private String firstName;

    @NotEmpty(message = "lastName is a required parameter")
    private String lastName;

    public NewUserVo() {
        super();
    }

    public NewUserVo(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "NewUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
