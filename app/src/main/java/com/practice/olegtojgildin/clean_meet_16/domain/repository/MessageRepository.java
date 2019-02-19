package com.practice.olegtojgildin.clean_meet_16.domain.repository;

import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;

import java.util.List;

/**
 * Created by olegtojgildin on 16/02/2019.
 */

public interface MessageRepository {
     String getWelcomeMessage();

     List<WeatherDay> getForesast();
}