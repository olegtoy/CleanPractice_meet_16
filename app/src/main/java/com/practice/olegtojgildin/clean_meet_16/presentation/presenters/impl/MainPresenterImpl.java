package com.practice.olegtojgildin.clean_meet_16.presentation.presenters.impl;


import android.content.Context;

import com.practice.olegtojgildin.clean_meet_16.domain.executor.Executor;
import com.practice.olegtojgildin.clean_meet_16.domain.executor.MainThread;
import com.practice.olegtojgildin.clean_meet_16.domain.interactors.WelcomingInteractor;
import com.practice.olegtojgildin.clean_meet_16.domain.interactors.impl.WelcomingInteractorImpl;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.repository.MessageRepository;
import com.practice.olegtojgildin.clean_meet_16.presentation.presenters.MainPresenter;
import com.practice.olegtojgildin.clean_meet_16.presentation.presenters.base.AbstractPresenter;

import java.util.List;

/**
 * Created by dmilicic on 12/13/15.
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        WelcomingInteractor.Callback {

    private MainPresenter.View mView;
    private MessageRepository mMessageRepository;
    private Context mContext;

    public MainPresenterImpl(Context context,Executor executor, MainThread mainThread,
                             View view, MessageRepository messageRepository) {
        super(executor, mainThread);
        mView = view;
        mMessageRepository = messageRepository;
        mContext=context;
    }

    @Override
    public void resume() {

        mView.showProgress();

        // initialize the interactor
        WelcomingInteractor interactor = new WelcomingInteractorImpl(mContext,
                mExecutor,
                mMainThread,
                this,
                mMessageRepository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
        mView.hideProgress();
        mView.displayWelcomeMessage(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }

    @Override
    public void onForecastRecieve(List<WeatherDay> forecast) {
        mView.displayForecast(forecast);
    }
}