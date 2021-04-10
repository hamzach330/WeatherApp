package com.hamza.weather.views.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.hamza.weather.R;

import com.hamza.weather.database.DatabaseClient;

import com.hamza.weather.model.WeatherData;
import com.hamza.weather.networkManager.APIManager;
import com.hamza.weather.networkManager.ApiResponse;
import com.hamza.weather.views.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;


/**
 * Created by Hamza Masood on 4/03/2019
 */

public class MainActivity extends AppCompatActivity {

    EditText search_ET;
    MaterialButton proceed_btn;
    IndexFastScrollRecyclerView weatherListView;
    RecyclerViewAdapter weatherListAdapter;
    TextView nodataTxt;
    ProgressBar progress;
    ArrayList<WeatherData> weatherDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

        //search_ET.setText("Islamabad");
        proceed_btn.setOnClickListener(v -> {
            hideKeyboard(MainActivity.this);
            nodataTxt.setVisibility(View.GONE);
            String cityName = search_ET.getText().toString();
            if (cityName.equals("")) {
                Toast.makeText(MainActivity.this, "Please Enter City Name",
                        Toast.LENGTH_LONG).show();
            } else {
                progress.setVisibility(View.VISIBLE);
                progress.bringToFront();
                getWeatherUpdate(cityName);
            }

        });


    }

    private void getWeatherUpdate(String CityName) {

        APIManager.getInstance().getWeather(new APIManager.Callback() {
            @Override
            public void onSuccess(int code, ApiResponse response) {
                progress.setVisibility(View.GONE);
                weatherDataArrayList = response.getData();
                weatherListAdapter = new RecyclerViewAdapter(MainActivity.this, response.getData());
                weatherListView.setAdapter(weatherListAdapter);

                new SaveTask().execute();
            }

            @Override
            public void onFailure(int code, String error) {
                progress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error,
                        Toast.LENGTH_LONG).show();
            }
        }, CityName);

    }

    private void initView() {
        search_ET = findViewById(R.id.search_ET);
        proceed_btn = findViewById(R.id.proceed_btn);
        weatherListView = findViewById(R.id.weatherList);
        progress = findViewById(R.id.progress);
        nodataTxt = findViewById(R.id.nodataTxt);
        progress.setVisibility(View.GONE);
        nodataTxt.setVisibility(View.VISIBLE);
        weatherListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        weatherListView.setIndexBarVisibility(false);

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < weatherDataArrayList.size(); i++){
                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().getDAO().insertMainData(weatherDataArrayList.get(i).getMain());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


}
