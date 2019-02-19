package com.practice.olegtojgildin.clean_meet_16.presentation.presenters;

import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.presentation.presenters.base.BasePresenter;
import com.practice.olegtojgildin.clean_meet_16.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by olegtojgildin on 16/02/2019.
 */
public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayWelcomeMessage(String msg);
        void displayForecast(List<WeatherDay> forecast);
    }
}
