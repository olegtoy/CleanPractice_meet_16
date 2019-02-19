package com.practice.olegtojgildin.clean_meet_16.data.repository.datasource;

import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;

import java.util.List;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public class ForecastRepositoryImpl implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        return mDataSourceFactory.create().getWelcomeMessage();
    }

    @Override
    public List<WeatherDay> getForesast() {
        return mDataSourceFactory.create().getForesast();
    }

    private final ForecastsDataSourceFactory mDataSourceFactory;
    private static volatile ForecastRepositoryImpl INSTANCE;

    public static ForecastRepositoryImpl getInstance(final ForecastsDataSourceFactory factory) {
        ForecastRepositoryImpl instance = INSTANCE;
        if (instance == null) {
            synchronized (ForecastRepositoryImpl.class) {
                instance = INSTANCE;
                if (instance == null) {
                    instance = INSTANCE = new ForecastRepositoryImpl(factory);
                }
            }
        }
        return instance;
    }

    private ForecastRepositoryImpl(final ForecastsDataSourceFactory factory) {
        this.mDataSourceFactory = factory;
    }

}
