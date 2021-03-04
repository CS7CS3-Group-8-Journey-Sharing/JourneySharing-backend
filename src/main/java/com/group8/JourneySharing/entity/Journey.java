package com.group8.JourneySharing.entity;

import com.sun.jmx.snmp.Timestamp;
import org.springframework.data.annotation.Id;

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
    private Timestamp startTime;
    private Timestamp endTime;
    private ModeOfTransport modeOfTransport;
    private List<Location> stops;

    public Journey() {
    }

    public Journey(String journeyId, boolean recurring, boolean completed, User owner, List<User> participants,
                   Location startLocation, Location endLocation, int maxParticipants, Timestamp startTime,
                   Timestamp endTime, ModeOfTransport modeOfTransport, List<Location> stops) {
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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
