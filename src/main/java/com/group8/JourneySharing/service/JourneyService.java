package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.vo.NewJourneyVo;

import java.util.List;

public interface JourneyService {


    Journey createJourney(NewJourneyVo newJourney);

    Journey getJourneyByID(String journeyID);

    Journey deleteRecurring(String journeyId);

    List<Journey> getJourneys(String userEmail);

    List<Journey> getHistory(List<String> journeys);

    List<Journey> getJourneysWithinRadius(double lat, double lng, int radius);

    Journey saveJourney(Journey journey);


}
