package com.practice.olegtojgildin.clean_meet_16.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import com.practice.olegtojgildin.clean_meet_16.data.database.DBManager;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;
import com.practice.olegtojgildin.clean_meet_16.presentation.ui.activities.MainActivity;

import java.util.List;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class ForecastsLocalDataSource implements MessageRepository {

    private  Context mContext;

    public ForecastsLocalDataSource(Context context) {
        mContext=context;
    }

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
        Log.d("from","bd");
        DBManager dbManager = DBManager.getInstance(mContext);
        return dbManager.getAllWeatherDay();
    }
}