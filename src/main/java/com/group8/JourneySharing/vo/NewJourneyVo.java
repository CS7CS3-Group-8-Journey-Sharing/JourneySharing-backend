package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Location;
import com.group8.JourneySharing.entity.ModeOfTransport;
import com.group8.JourneySharing.entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class NewJourneyVo {


    private boolean recurring;
    @NotNull(message = "Owner is a required parameter")
    private User owner;
    @NotNull(message = "Start location is a required parameter")
    private Location startLocation;
    @NotNull(message = "End Location is a required parameter")
    private Location endLocation;

    private int maxParticipants;
    @NotNull(message = "Participants is a required parameter")
    private ArrayList<User> participants;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "StartTime is a required parameter")
    private Date startTime;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "EndTime is a required parameter")
    private Date endTime;
    @NotNull(message = " Mode of transport is a required parameter")
    private ModeOfTransport modeOfTransport;


    public NewJourneyVo() {
        super();
    }

    public NewJourneyVo(boolean recurring, User owner, Location startLocation, Location endLocation, int maxParticipants, ArrayList<User> participants, Date startTime, Date endTime, ModeOfTransport modeOfTransport) {
        this.recurring = recurring;
        this.owner = owner;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.maxParticipants = maxParticipants;
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = endTime;
        this.modeOfTransport = modeOfTransport;
    }


    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(ModeOfTransport modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    @Override
    public String toString() {
        return "NewJourneyVo{" +
                "recurring=" + recurring +
                ", owner=" + owner +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", maxParticipants=" + maxParticipants +
                ", participants=" + participants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modeOfTransport=" + modeOfTransport +
                '}';
    }
}
