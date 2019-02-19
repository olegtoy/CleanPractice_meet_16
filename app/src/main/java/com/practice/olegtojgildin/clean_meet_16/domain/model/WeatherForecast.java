package com.practice.olegtojgildin.clean_meet_16.domain.model;

/**
 * Created by olegtojgildin on 16/02/2019.
 */


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by olegtojgildin on 31/01/2019.
 */

public class WeatherForecast {
    @SerializedName("list")
    private List<WeatherDay> items;

    public WeatherForecast(List<WeatherDay> items) {
        this.items = items;
    }

    public List<WeatherDay> getItems() {
        return items;
    }
}