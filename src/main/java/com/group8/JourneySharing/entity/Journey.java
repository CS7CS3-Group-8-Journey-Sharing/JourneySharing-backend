package com.group8.JourneySharing.entity;


import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Journey {

    @Id
    private String journeyId;
    private boolean recurring;
    private boolean completed;
    private User owner;
    private List<User> participants;
    private Location startLocation;
    private Location endLocation;
    private int maxParticipants;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endTime;
    private ModeOfTransport modeOfTransport;
    private List<Location> stops;

    public Journey() {
    }
// Add timestap Timestamp startTime,
    //Timestamp endTime,
    public Journey(String journeyId, boolean recurring, boolean completed, User owner, List<User> participants,
                   Location startLocation, Location endLocation, int maxParticipants, Date startTime,Date endTime, ModeOfTransport modeOfTransport, List<Location> stops) {
        this.journeyId = journeyId;
        this.recurring = recurring;
        this.completed = completed;
        this.owner = owner;
        this.participants = participants;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
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

    public List<Location> getStops() {
        return stops;
    }

    public void setStops(List<Location> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "journeyId='" + journeyId + '\'' +
                ", recurring=" + recurring +
                ", completed=" + completed +
                ", owner=" + owner +
                ", participants=" + participants +
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
