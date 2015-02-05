package com.dat.mobile.sunshine;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dat.mobile.sunshine.model.domain.Forecast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {
    ArrayAdapter<String> forecastAdapter; //moved here so that it is global

    public ForecastFragment() {
    }

    //ADDED -- this makes sture that we have the options menu present
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //ADDED this actually display the menu on the screen
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //CREATE MOCK DATA
        String[] forecastArray =  {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };

        //MAKE IT INTO A LIST
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

//GET THE REAL DATA


        //Create a LIST ADAPTER- tie to layout and a data-view in it
        forecastAdapter = new ArrayAdapter<String> (
                getActivity(),R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,weekForecast
        );
        //Find a list-view and bind it to the adapter
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(forecastAdapter);

        Log.v(">>>>>>","here");

        //AdapterView.OnItemClickListener
        //listView.setOnItemClickListener(onclickItemlistener);

        return rootView;
    }

    //ADDED this makes sure that when settings is clicked we have the refresh button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            //Add this to execute the Async Task
            FetchWeatherTask ft = new FetchWeatherTask();
            Log.v("onOptionsItemSelected","fresh retrieval");
            ft.execute("02421");
            List<Forecast> ff = null;
            try {
                 ff = ft.get();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("xx", e.getLocalizedMessage());
            }
            forecastAdapter.clear();
            for(Forecast f:ff){
                forecastAdapter.add(f.toString());
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
