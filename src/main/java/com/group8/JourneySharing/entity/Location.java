package com.group8.JourneySharing.entity;

import org.springframework.data.annotation.Id;

public class Location {

    @Id
    public String locationId;
    public double lat;
    public double lng;

    public Location() {
    }

    public Location(String locationId, double lat, double lng) {
        this.locationId = locationId;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId='" + locationId + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
