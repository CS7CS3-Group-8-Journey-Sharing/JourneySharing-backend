package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Journey;

import java.util.List;

public class UserDetailsVo {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String iban;
    private List<String> history;


    public UserDetailsVo(){
    }

    public UserDetailsVo(String email, String firstName, String lastName, String mobileNumber, String iban, List<String> history) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.iban = iban;
        this.history = history;
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

    public void setHistory(List<String> history) {
        this.history = history;
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

    public List<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "userDetailsVo{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", history=" + history +
                '}';
    }
}
