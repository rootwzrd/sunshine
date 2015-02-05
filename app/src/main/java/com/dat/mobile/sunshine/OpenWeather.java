package com.dat.mobile.sunshine;

import android.net.Uri;
import android.util.Log;

import com.dat.mobile.sunshine.model.domain.Forecast;
import com.dat.mobile.sunshine.model.service.Lst;
import com.dat.mobile.sunshine.model.service.WeatherResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by burcoral on 1/2/15.
 */
public class OpenWeather {
    private String format = "json";
    private String units = "metric";
    private int numDays = 7;
    //this was the first implementation
    private String forecastJsonStr = null;//the raw JSON response as a string.
    //this is the new implementation
    private List<Forecast> forecastList = new ArrayList<Forecast>();
    public OpenWeather() {
        super();
    }

    //Note that there is a slight diff from udacity. This class does not exist on udacity.
    //I am returning an object to the Task. This way can do testing.
    //passing in the zipCode from the task.
    public List<Forecast> getWeatherData(String zipCode) {
        // These two need to be declared outside the try/catch
    // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        try {

            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+zipCode+"&mode=json&units=metric&cnt=7");

            //THe below does not work with the unit tests running outside Android JVM...
            //You have to put runwith plus emulate 18!
            final String FORECAST_BASE_URL =
                    "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, zipCode)
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                    .build();

            URL url = new URL(builtUri.toString());


            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();
            Log.v("OpenWeather",forecastJsonStr);
            Gson gson  = new Gson();
            WeatherResponse wr = gson.fromJson(forecastJsonStr,WeatherResponse.class);
            Log.v("WeatherResponse","adadada");
            List<Lst> results =wr.getLst();
            for(Lst r:results) {
                Forecast f = new Forecast();
                Date date = new Date(r.getDt().intValue() * 1000);
                SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
                f.setDesc(r.getWeather().get(0).getMain());
                f.setDay(format.format(date).toString());
                f.setMin(Long.toString(Math.round(r.getTemp().getMin().doubleValue())));
                f.setMax(Long.toString(Math.round(r.getTemp().getMax().doubleValue())));
                forecastList.add(f);
            }

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            forecastJsonStr = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return forecastList;
    }
}
