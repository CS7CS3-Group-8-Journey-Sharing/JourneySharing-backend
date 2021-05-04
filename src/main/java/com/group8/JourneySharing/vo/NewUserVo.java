package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Gender;

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

    private int age;

    private Gender gender = Gender.NONE;

    public NewUserVo() {
        super();
    }

    public NewUserVo(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public NewUserVo(String email, String password, String firstName, String lastName, int age, Gender gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
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
        return "NewUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
