package com.x.weather.provider.openweather;

import com.x.domain.model.Location;
import com.x.weather.CoOrdinate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class OpenWeatherHttpDataSourceTest {

    @Autowired
    private OpenWeatherHttpDataSource openWeatherHttpDataSource;

    @Test
    void query() {
        Location location = new Location("10.99","44.34");
        assertTrue(!openWeatherHttpDataSource.makeHttpRequestToOpenWeatherApi(location).isEmpty());
    }
}