package com.group8.JourneySharing.vo;

import java.util.ArrayList;
import java.util.List;

public class PaymentVo {

    private String ownerEmail;

    private String iban;

    private String mobileNumber;

    private double price;

    private List<String> participants = new ArrayList<>();

    public PaymentVo(String ownerEmail, String iban, String mobileNumber, double price, List<String> participants) {
        this.ownerEmail = ownerEmail;
        this.iban = iban;
        this.mobileNumber = mobileNumber;
        this.price = price;
        this.participants = participants;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "PaymentVo{" +
                "ownerEmail='" + ownerEmail + '\'' +
                ", iban='" + iban + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", price=" + price +
                ", participants=" + participants +
                '}';
    }
}
