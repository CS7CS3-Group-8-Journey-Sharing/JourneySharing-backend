package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "request")
public class Requests {

    @Id
    private String requestId;
    private String requestedUserEmail;
    private String journeyId;
    private String journeyName;
    private RequestStatus requestStatus = RequestStatus.pending;
    private ViewStatus viewStatus = ViewStatus.unseen;

    public Requests() {
    }

    public Requests(String requestedUserEmail, String journeyId, String journeyName) {
        this.requestedUserEmail = requestedUserEmail;
        this.journeyId = journeyId;
        this.journeyName = journeyName;
    }

    public Requests(String requestId, String requestedUserEmail, String journeyId, String journeyName, RequestStatus requestStatus, ViewStatus viewStatus) {
        this.requestId = requestId;
        this.requestedUserEmail = requestedUserEmail;
        this.journeyId = journeyId;
        this.journeyName = journeyName;
        this.requestStatus = requestStatus;
        this.viewStatus = viewStatus;
    }

    public Requests(String requestId, String requestedUserEmail, String journeyId, RequestStatus pending, ViewStatus unseen) {
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

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
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
