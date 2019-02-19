package com.practice.olegtojgildin.clean_meet_16.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;


import com.practice.olegtojgildin.clean_meet_16.R;
import com.practice.olegtojgildin.clean_meet_16.data.repository.datasource.ForecastRepositoryImpl;
import com.practice.olegtojgildin.clean_meet_16.data.repository.datasource.ForecastsDataSourceFactory;
import com.practice.olegtojgildin.clean_meet_16.domain.executor.impl.ThreadExecutor;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.presentation.presenters.MainPresenter;
import com.practice.olegtojgildin.clean_meet_16.presentation.presenters.impl.MainPresenterImpl;
import com.practice.olegtojgildin.clean_meet_16.threading.MainThreadImpl;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainPresenter.View, WeatherAdapter.OnWeatherListener{
    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<WeatherDay> mListForecast;

    TextView mWelcomeTextView;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcomeTextView=findViewById(R.id.textview);
        initViews();
        final ForecastsDataSourceFactory factory = new ForecastsDataSourceFactory(this);

        mPresenter = new MainPresenterImpl(MainActivity.this,
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
              //  new WelcomeMessageRepository()
       ForecastRepositoryImpl.getInstance(factory)

        );
    }

    public void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.addItemDecoration(LinearSpacingItemDecoration.newBuilder()
                .spacing(getResources().getDimensionPixelSize(R.dimen.margin_half))
                .orientation(LinearLayoutManager.VERTICAL)
                .includeEdge(true)
                .build());


    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void displayForecast(List<WeatherDay> forecast) {
        mAdapter = new WeatherAdapter(forecast, MainActivity.this);
        mListForecast=forecast;
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onWeatherClick(int position) {
        Intent intent = DetailsWeatherActivity.newIntent(this);
        intent.putExtra(WeatherDay.class.getCanonicalName(), mListForecast.get(position));
        startActivity(intent);
    }

}