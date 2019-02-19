package com.practice.olegtojgildin.clean_meet_16.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;

/**
 * Created by olegtojgildin on 17/02/2019.
 */


public final class ForecastsDataSourceFactory {

    private final Context mContext;

    public ForecastsDataSourceFactory(@NonNull final Context context) {
        this.mContext = context;
    }

    public MessageRepository create() {
        MessageRepository dataSource;

        if (NetworkManager.isNetworkAvailable(mContext)) {
            dataSource = createRemoteDataSource();
        } else {

            dataSource = createLocalDataSource();
        }
        return dataSource;
    }

    public MessageRepository createLocalDataSource() {
        return new ForecastsLocalDataSource(mContext);
    }

    public MessageRepository createRemoteDataSource() {

        return new ForecastsRemoteDataSource(mContext);
    }
}
