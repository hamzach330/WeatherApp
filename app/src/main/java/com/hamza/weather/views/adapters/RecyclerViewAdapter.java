package com.hamza.weather.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hamza.weather.R;
import com.hamza.weather.model.WeatherData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<WeatherData> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public RecyclerViewAdapter(Context context, List<WeatherData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherData data = mData.get(position);
        holder.weatherDes.setText(data.getWeather().get(0).getDescription().toUpperCase());
        holder.currentTemp.setText(convertFromKelvinToCelsius((int) data.getMain().getTemp()) +"°C");
        holder.realfeelTemp.setText("Real Feel \n"+ convertFromKelvinToCelsius((int) data.getMain().getFeelsLike()) +"°C");
        holder.minTemp.setText("Min Temp : "+ convertFromKelvinToCelsius((int) data.getMain().getTempMin())+"°C");
        holder.maxTemp.setText("Max Temp : "+ convertFromKelvinToCelsius((int) data.getMain().getTempMax())+"°C");
        holder.humidity.setText("Humidity : "+ data.getMain().getHumidity());
        holder.wind.setText("Wind Speed : "+ data.getWind().getSpeed());
        holder.time.setText("Time : "+getDateTime(data.getDtTxt()).toGMTString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView weatherDes, currentTemp, realfeelTemp, minTemp, maxTemp, humidity, wind, time;

        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = itemView.findViewById(R.id.tvAnimalName);
            weatherDes = itemView.findViewById(R.id.weatherDes);
            currentTemp = itemView.findViewById(R.id.currentTemp);
            realfeelTemp = itemView.findViewById(R.id.realfeelTemp);
            minTemp = itemView.findViewById(R.id.minTemp);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            humidity = itemView.findViewById(R.id.humidity);
            wind = itemView.findViewById(R.id.wind);
            time = itemView.findViewById(R.id.time);

        }


    }


    private static Integer convertFromKelvinToCelsius(Integer value) {
        return Integer.valueOf((int) (value - 273.15));
    }

    private Date getDateTime (String Date)
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        return date;
    }

}