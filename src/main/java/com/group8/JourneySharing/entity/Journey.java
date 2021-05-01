package com.group8.JourneySharing.entity;


import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.ArrayList;

public class Journey {

    @Id
    private String journeyId;
    private String name;
    private boolean recurring;
    private boolean completed;
    private String ownerEmail;
    private ArrayList<String> participantEmails;
    private Location startLocation;
    private Location endLocation;
    private int maxParticipants;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    private Date endTime;
    private ModeOfTransport modeOfTransport;
    private ArrayList<Location> stops;

    public Journey() {
    }

    public Journey(String journeyId, String name, boolean recurring, boolean completed, String ownerEmail, ArrayList<String> participantEmails,
                   Location startLocation, Location endLocation, int maxParticipants, Date startTime, Date endTime, ModeOfTransport modeOfTransport, ArrayList<Location> stops) {
        this.journeyId = journeyId;
        this.name = name;
        this.recurring = recurring;
        this.completed = completed;
        this.ownerEmail = ownerEmail;
        this.participantEmails = participantEmails;
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public ArrayList<String> getParticipantEmails() {
        return participantEmails;
    }

    public void setParticipantEmails(ArrayList<String> participantEmails) {
        this.participantEmails = participantEmails;
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

    public void addParticipant(String participantEmail) {
        participantEmails.add(participantEmail);
    }

    public void removeParticipant(String participantEmail) {
        participantEmails.remove(participantEmail);
    }


    @Override
    public String toString() {
        return "Journey{" +
                "journeyId='" + journeyId + '\'' +
                ", name='" + name +
                ", recurring=" + recurring +
                ", completed=" + completed +
                ", ownerEmail=" + ownerEmail +
                ", participantEmails=" + participantEmails +
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
