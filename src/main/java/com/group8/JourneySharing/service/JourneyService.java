package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.vo.NewJourneyVo;

import java.util.List;

public interface JourneyService {


    Journey createJourney(NewJourneyVo newJourney);

    Journey getJourneyByID(String journeyID);

    List<Journey> getJourneysWithinRadius(double lat, double lng, int radius);
}
