package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.Location;
import com.group8.JourneySharing.entity.ModeOfTransport;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.ArrayList;

public class NewJourneyVo {


    @NotNull(message = "Journey name is a required parameter")
    private String name;
    private boolean recurring;
    @NotNull(message = "Owner ID is a required parameter")
    private String ownerId;
    @NotNull(message = "Start location is a required parameter")
    private Location startLocation;
    @NotNull(message = "End Location is a required parameter")
    private Location endLocation;

    private int maxParticipants;
    @NotNull(message = "Participants is a required parameter")
    private ArrayList<String> participantIds;

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

    public NewJourneyVo(String name, boolean recurring, String ownerId, Location startLocation, Location endLocation, int maxParticipants, ArrayList<String> participantIds, Date startTime, Date endTime, ModeOfTransport modeOfTransport) {
        this.name = name;
        this.recurring = recurring;
        this.ownerId = ownerId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.maxParticipants = maxParticipants;
        this.participantIds = participantIds;
        this.startTime = startTime;
        this.endTime = endTime;
        this.modeOfTransport = modeOfTransport;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public ArrayList<String> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantsIds(ArrayList<String> participantIds) {
        this.participantIds = participantIds;
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
                "name=" + name +
                ", recurring=" + recurring +
                ", ownerId=" + ownerId +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", maxParticipants=" + maxParticipants +
                ", participantIds=" + participantIds +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modeOfTransport=" + modeOfTransport +
                '}';
    }
}
