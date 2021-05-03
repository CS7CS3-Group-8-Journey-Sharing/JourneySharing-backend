package com.group8.JourneySharing.vo;

import com.group8.JourneySharing.entity.RequestStatus;
import com.group8.JourneySharing.entity.ViewStatus;

public class RequestsVo {

    private String requestId;
    private UserDetailsVo requestedUser;
    private String journeyId;
    private String journeyName;
    private RequestStatus requestStatus;
    private ViewStatus viewStatus;

    public RequestsVo() {
    }

    public RequestsVo(String requestId, UserDetailsVo requestedUser, String journeyId, String journeyName, RequestStatus requestStatus, ViewStatus viewStatus) {
        this.requestId = requestId;
        this.requestedUser = requestedUser;
        this.journeyId = journeyId;
        this.journeyName = journeyName;
        this.requestStatus = requestStatus;
        this.viewStatus = viewStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public UserDetailsVo getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(UserDetailsVo requestedUser) {
        this.requestedUser = requestedUser;
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
        return "RequestsVo{" +
                "requestId='" + requestId + '\'' +
                ", requestedUser=" + requestedUser +
                ", journeydId='" + journeyId + '\'' +
                ", journeydName='" + journeyName + '\'' +
                ", requestStatus=" + requestStatus +
                ", viewStatus=" + viewStatus +
                '}';
    }
}
