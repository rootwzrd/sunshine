package com.dat.mobile.sunshine;

import com.dat.mobile.sunshine.model.domain.Forecast;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;


@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, qualifiers = "land")
public class OpenWeatherTest {

    @Test
    public void thisAlwaysPasses() {
    }
    @Test
    @Ignore
    public void thisIsIgnored() {
    }

    @Test
    public void testWeatherData() {
        OpenWeather ow = new OpenWeather();
        List<Forecast> result = ow.getWeatherData("02119");
        Assert.assertNotNull(result);
        System.out.println("weather" + result);

    }
}
