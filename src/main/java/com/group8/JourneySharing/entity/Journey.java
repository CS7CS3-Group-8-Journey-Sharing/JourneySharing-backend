package com.group8.JourneySharing.entity;


import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.ArrayList;

public class Journey {

    @Id
    private String journeyId;
    private boolean recurring;
    private boolean completed;
    private String ownerId;
    private ArrayList<String> participantIds;
    private Location startLocation;
    private Location endLocation;
    private int maxParticipants;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endTime;
    private ModeOfTransport modeOfTransport;
    private ArrayList<Location> stops;

    public Journey() {
    }
// Add timestap Timestamp startTime,
    //Timestamp endTime,
    public Journey(String journeyId, boolean recurring, boolean completed, String ownerId, ArrayList<String> participantIds,
                   Location startLocation, Location endLocation, int maxParticipants, Date startTime, Date endTime, ModeOfTransport modeOfTransport, ArrayList<Location> stops) {
        this.journeyId = journeyId;
        this.recurring = recurring;
        this.completed = completed;
        this.ownerId = ownerId;
        this.participantIds = participantIds;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.maxParticipants = maxParticipants;
        this.startTime = startTime;
        this.endTime = endTime;
        this.modeOfTransport = modeOfTransport;
        this.stops = stops;
    }

    public String getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(String journeyId) {
        this.journeyId = journeyId;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getOwner() {
        return ownerId;
    }

    public void setOwner(String ownerId) {
        this.ownerId = ownerId;
    }

    public ArrayList<String> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(ArrayList<String> participantIds) {
        this.participantIds = participantIds;
    }

    public void addParticipant(String participantId) {
        participantIds.add(participantId);
    }

    public void removeParticipant(String participantId) {
        participantIds.remove(participantId);
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

    public ArrayList<Location> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Location> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "journeyId='" + journeyId + '\'' +
                ", recurring=" + recurring +
                ", completed=" + completed +
                ", ownerId=" + ownerId +
                ", participantIds=" + participantIds +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", maxParticipants=" + maxParticipants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modeOfTransport=" + modeOfTransport +
                ", stops=" + stops +
                '}';
    }
}
