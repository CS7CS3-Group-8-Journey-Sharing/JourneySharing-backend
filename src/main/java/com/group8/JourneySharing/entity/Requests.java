package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "request")
public class Requests {

    @Id
    private String requestId;
    private String requestedUserEmail;
    private String journeyId;
    private RequestStatus requestStatus = RequestStatus.pending;
    private ViewStatus viewStatus = ViewStatus.unseen;

    public Requests() {
    }

    public Requests(String requestedUserEmail, String journeyId) {
        this.requestedUserEmail = requestedUserEmail;
        this.journeyId = journeyId;
    }

    public Requests(String requestId, String requestedUserEmail, String journeyId, RequestStatus requestStatus, ViewStatus viewStatus) {
        this.requestId = requestId;
        this.requestedUserEmail = requestedUserEmail;
        this.journeyId = journeyId;
        this.requestStatus = requestStatus;
        this.viewStatus = viewStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestedUserEmail() {
        return requestedUserEmail;
    }

    public void setRequestedUserEmail(String requestedUserEmail) {
        this.requestedUserEmail = requestedUserEmail;
    }

    public String getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(String journeyId) {
        this.journeyId = journeyId;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public ViewStatus getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(ViewStatus viewStatus) {
        this.viewStatus = viewStatus;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "requestId='" + requestId + '\'' +
                ", requestedUserEmail='" + requestedUserEmail + '\'' +
                ", journeydId='" + journeyId + '\'' +
                ", requestStatus=" + requestStatus + '\'' +
                ", viewStatus=" + viewStatus +
                '}';
    }
}
