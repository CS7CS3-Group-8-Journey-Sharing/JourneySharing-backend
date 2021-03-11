package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Journey;

import java.util.List;

public class UserDetailsVo {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String iban;
    private List<Journey> history;


    public UserDetailsVo(){
    }

    public UserDetailsVo(String userName, String firstName, String lastName, String email, String mobileNumber, String iban, List<Journey> history) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.iban = iban;
        this.history = history;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setHistory(List<Journey> history) {
        this.history = history;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getIban() {
        return iban;
    }

    public List<Journey> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "userDetailsVo{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", history=" + history +
                '}';
    }
}
