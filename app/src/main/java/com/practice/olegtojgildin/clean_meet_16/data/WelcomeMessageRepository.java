package com.practice.olegtojgildin.clean_meet_16.data;


import android.os.AsyncTask;
import android.util.Log;

import com.practice.olegtojgildin.clean_meet_16.data.database.DBManager;
import com.practice.olegtojgildin.clean_meet_16.data.network.ApiMapper;
import com.practice.olegtojgildin.clean_meet_16.data.network.RetrofitHelper;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;
import com.practice.olegtojgildin.clean_meet_16.presentation.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by olegtojgildin on 16/02/2019.
 */

public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return msg;
    }

    @Override
    public List<WeatherDay> getForesast() {
        List<WeatherDay> forecast;
        ApiMapper mApiMapper = new ApiMapper(new RetrofitHelper());

        forecast = mApiMapper.getForecastWeatherSync("Moscow");
        return forecast;

    }

}