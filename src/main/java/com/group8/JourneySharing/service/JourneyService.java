package com.group8.JourneySharing.service;

import com.group8.JourneySharing.entity.Journey;
import com.group8.JourneySharing.vo.NewJourneyVo;

public interface JourneyService {


    Journey createJourney(NewJourneyVo newJourney);
}
