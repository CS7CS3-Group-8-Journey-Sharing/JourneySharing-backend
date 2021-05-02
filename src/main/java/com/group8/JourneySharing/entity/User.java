package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(value = "user")
public class User {

    @Id
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String iban;
    private List<String> history;
    private int age;
    private Gender gender;
    private Rating rating = new Rating();

    public User() {
    }

    public User(String userId, String password,
                String firstName, String lastName, String email, String mobileNumber,
                String iban, List<String> history, Rating rating) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.iban = iban;
        this.history = history;
        this.rating = rating;
    }

    public User(String userId, String password, String firstName, String lastName, String email,
                String mobileNumber, String iban, List<String> history, int age,
                Gender gender, Rating rating) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.iban = iban;
        this.history = history;
        this.age = age;
        this.gender = gender;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public void addHistory(String journeyId) {
        history.add(journeyId);
    }

    public void removeHistory(String journeyId) {
        history.remove(journeyId);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", history=" + history +
                ", rating=" + rating +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
