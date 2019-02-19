package com.practice.olegtojgildin.clean_meet_16.domain.interactors;


import com.practice.olegtojgildin.clean_meet_16.domain.interactors.base.Interactor;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;

import java.util.List;

/**
 * Created by olegtojgildin on 16/02/2019.
 */


public interface WelcomingInteractor extends Interactor {

    interface Callback {

        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);

        void onForecastRecieve(List<WeatherDay> forecast);
    }
}