package com.practice.olegtojgildin.clean_meet_16.domain.interactors.impl;


import android.content.Context;

import com.practice.olegtojgildin.clean_meet_16.data.repository.datasource.ForecastRepositoryImpl;
import com.practice.olegtojgildin.clean_meet_16.data.repository.datasource.ForecastsDataSourceFactory;
import com.practice.olegtojgildin.clean_meet_16.domain.executor.Executor;
import com.practice.olegtojgildin.clean_meet_16.domain.executor.MainThread;
import com.practice.olegtojgildin.clean_meet_16.domain.interactors.WelcomingInteractor;
import com.practice.olegtojgildin.clean_meet_16.domain.interactors.base.AbstractInteractor;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;

import java.util.List;

/**
 * Created by olegtojgildin on 16/02/2019.
 */

public class WelcomingInteractorImpl extends AbstractInteractor implements WelcomingInteractor {


    private WelcomingInteractor.Callback mCallback;
    private MessageRepository mMessageRepository;
    private Context mContext;

    public WelcomingInteractorImpl(Context context,Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, MessageRepository messageRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mMessageRepository = messageRepository;
        mContext=context;

    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final String msg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(msg);
            }
        });
    }
    private void postForecast(final List<WeatherDay> forecast) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onForecastRecieve(forecast);
            }
        });
    }

    @Override
    public void run() {
        // получение сообщения
        final ForecastsDataSourceFactory factory = new ForecastsDataSourceFactory(mContext);
        final ForecastRepositoryImpl repository = ForecastRepositoryImpl.getInstance(factory);

        final String message = mMessageRepository.getWelcomeMessage();
        List<WeatherDay> forecast=repository.getForesast();

        // проверяем, получили ли мы сообщение
        if (message == null || message.length() == 0||forecast.size()==0) {
            // уведомляем об ошибке основной поток
            notifyError();
            return;
        }
        // мы получили наше сообщение, уведомляем об этом UI в основном потоке
        postMessage(message);
        postForecast(forecast);
    }
}