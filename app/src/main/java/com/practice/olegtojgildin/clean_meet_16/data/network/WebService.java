package com.practice.olegtojgildin.clean_meet_16.data.network;


import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by olegtojgildin on 31/01/2019.
 */

public interface WebService {

    @GET("weather?")
    Call<WeatherDay> getToday(@Query("q") String cityName,
                              @Query("appid") String appid);

    @GET("forecast?")
    Call<WeatherForecast> getForecast(@Query("q") String cityName,
                                      @Query("mode") String mode,
                                      @Query("units") String units,
                                      @Query("lang") String lang,
                                      @Query("appid") String appid);
}
