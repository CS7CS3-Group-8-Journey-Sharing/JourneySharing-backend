package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Gender;

public class EditUserVo {
    private String password;
    private String mobileNumber;
    private String iban;
    private Integer age;
    private Gender gender;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
        return "EditUserVo{" +
                "password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
