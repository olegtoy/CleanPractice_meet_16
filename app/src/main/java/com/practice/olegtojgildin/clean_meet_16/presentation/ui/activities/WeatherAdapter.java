package com.practice.olegtojgildin.clean_meet_16.presentation.ui.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.olegtojgildin.clean_meet_16.R;
import com.practice.olegtojgildin.clean_meet_16.domain.model.WeatherDay;
import com.practice.olegtojgildin.clean_meet_16.presentation.DateUtils;


import java.util.List;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolderWeather> {
    private List<WeatherDay> mWeatherForecast;
    private OnWeatherListener mOnWeatherListener;

    public WeatherAdapter(List<WeatherDay> listWeather, OnWeatherListener onWeatherListener) {
        mWeatherForecast = listWeather;
        this.mOnWeatherListener = onWeatherListener;
    }


    @Override
    public ViewHolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolderWeather(view, mOnWeatherListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderWeather holder, int position) {
        holder.temp.setText(Float.toString(mWeatherForecast.get(position).getMain().getTemp())+"°C");
        if (mWeatherForecast.get(position).getWeather().size() != 0)
            holder.main.setText(mWeatherForecast.get(position).getWeather().get(0).getDescription());
        else
            holder.main.setText("Описание");

        //java.util.Date time = new java.util.Date((long) mWeatherForecast.get(position).getDt() * 1000);

        holder.date.setText(DateUtils.covertDate(mWeatherForecast.get(position).getDt() ));

    }

    @Override
    public int getItemCount() {
        return mWeatherForecast.size();
    }

    public static class ViewHolderWeather extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView temp;
        public TextView main;
        public TextView date;

        OnWeatherListener onWeatherListener;

        public ViewHolderWeather(View v, OnWeatherListener onWeatherListener) {
            super(v);
            temp = v.findViewById(R.id.temp);
            main = v.findViewById(R.id.main);
            date = v.findViewById(R.id.date);
            v.setOnClickListener(this);
            this.onWeatherListener = onWeatherListener;
        }

        @Override
        public void onClick(View view) {
            onWeatherListener.onWeatherClick(getAdapterPosition());
        }
    }

    public interface OnWeatherListener {
        void onWeatherClick(int position);
    }
}
