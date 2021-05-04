package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Location;
import com.group8.JourneySharing.entity.ModeOfTransport;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.ArrayList;

public class NewJourneyVo {


    @NotBlank(message = "Journey name is a required parameter and cannot be an empty string")
    private String name;
    private boolean recurring;
    @NotNull(message = "Recurring days is a required parameter")
    private ArrayList<Boolean> recurringDays;
    @NotNull(message = "Owner Email is a required parameter")
    private String ownerEmail;
    @NotNull(message = "Start location is a required parameter")
    private Location startLocation;
    @NotNull(message = "End Location is a required parameter")
    private Location endLocation;
    @NotNull(message = "Max Participants is a required parameter")
    private Integer maxParticipants;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "StartTime is a required parameter")
    private Date startTime;

    @NotNull(message = " Mode of transport is a required parameter")
    private ModeOfTransport modeOfTransport;

    private boolean womanOnly = false;

    private Double price = 0.0 ;


    public NewJourneyVo() {
        super();
    }

    public NewJourneyVo(String name, boolean recurring, ArrayList<Boolean> recurringDays, String ownerEmail,
                        Location startLocation, Location endLocation, int maxParticipants, ArrayList<String> participantIds,
                        Date startTime, Date endTime, ModeOfTransport modeOfTransport, boolean womanOnly , Double price) {
        this.name = name;
        this.recurring = recurring;
        this.recurringDays = recurringDays;
        this.ownerEmail = ownerEmail;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.maxParticipants = maxParticipants;
        this.startTime = startTime;
        this.modeOfTransport = modeOfTransport;
        this.womanOnly = womanOnly;
        this.price = price;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public ArrayList<Boolean> getRecurringDays() { return recurringDays; }

    public void setRecurringDays(ArrayList<Boolean> recurringDays) { this.recurringDays = recurringDays; }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(ModeOfTransport modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public boolean isWomanOnly() {
        return womanOnly;
    }

    public void setWomanOnly(boolean womanOnly) {
        this.womanOnly = womanOnly;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "NewJourneyVo{" +
                "name=" + name +
                ", recurring=" + recurring +
                ", ownerEmail=" + ownerEmail +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", maxParticipants=" + maxParticipants +
                ", startTime=" + startTime +
                ", modeOfTransport=" + modeOfTransport +
                ", womanOnly=" + womanOnly +
                ", price=" + price +
                '}';
    }
}
