package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "request")
public class Requests {

    @Id
    private String requestId;
    private String requestedUserEmail;
    private String journeydId;
    private RequestStatus requestStatus = RequestStatus.pending;
    private ViewStatus viewStatus = ViewStatus.unseen;

    public Requests() {
    }

    public Requests(String requestedUserEmail, String journeydId) {
        this.requestedUserEmail = requestedUserEmail;
        this.journeydId = journeydId;
    }

    public Requests(String requestId, String requestedUserEmail, String journeydId, RequestStatus requestStatus, ViewStatus viewStatus) {
        this.requestId = requestId;
        this.requestedUserEmail = requestedUserEmail;
        this.journeydId = journeydId;
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

    public String getJourneydId() {
        return journeydId;
    }

    public void setJourneydId(String journeydId) {
        this.journeydId = journeydId;
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
                ", journeydId='" + journeydId + '\'' +
                ", requestStatus=" + requestStatus + '\'' +
                ", viewStatus=" + viewStatus +
                '}';
    }
}
