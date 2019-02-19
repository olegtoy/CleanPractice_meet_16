package com.practice.olegtojgildin.clean_meet_16.data.repository.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.practice.olegtojgildin.clean_meet_16.data.database.DBManager;
import com.practice.olegtojgildin.clean_meet_16.data.network.ApiMapper;
import com.practice.olegtojgildin.clean_meet_16.data.network.RetrofitHelper;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class ForecastsRemoteDataSource implements MessageRepository {

    private  Context mContext;

    public ForecastsRemoteDataSource(Context context) {
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

    @SuppressLint("StaticFieldLeak")
    @Override
    public List<WeatherDay> getForesast() {
        List<WeatherDay> forecast;
        ApiMapper mApiMapper = new ApiMapper(new RetrofitHelper());
        forecast = mApiMapper.getForecastWeatherSync("Moscow");

        DBManager dbManager = DBManager.getInstance(mContext);
        dbManager.removeForecasts();
        for (int i = 0; i < forecast.size(); i++)
            dbManager.addWeather(forecast.get(i));
        return forecast;

    }
}