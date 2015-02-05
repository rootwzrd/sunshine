package com.dat.mobile.sunshine;

import android.os.AsyncTask;
import android.util.Log;

import com.dat.mobile.sunshine.model.domain.Forecast;

import java.util.List;

/**
 * Created by burcoral on 1/3/15.
 */
public class FetchWeatherTask  extends AsyncTask<String,Void,List<Forecast>> {
    private final String LOG_TAG = "FetchWeatherTask";
    @Override
    protected List<Forecast> doInBackground(String... params) {
        String zipCode = "04917";
        if (params.length != 0) zipCode = params[0];
        try {
            List<Forecast> forecastList =  new OpenWeather().getWeatherData(zipCode);
            return forecastList;
        } catch (Exception e) {
            Log.e(LOG_TAG,e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //This is whee we are going to get he data back into the UI
    @Override
    protected void onPostExecute(List<Forecast> forecasts) {
        //super.onPostExecute(forecasts);
        if ( forecasts != null) {

        }
    }
}
