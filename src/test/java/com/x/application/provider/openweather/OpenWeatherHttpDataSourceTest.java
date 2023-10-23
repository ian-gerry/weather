package com.x.application.provider.openweather;

import com.x.application.CoOrdinate;
import com.x.application.provider.openweather.OpenWeatherHttpDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class OpenWeatherHttpDataSourceTest {

    @Autowired
    private OpenWeatherHttpDataSource openWeatherHttpDataSource;

    private static final CoOrdinate LONG = new CoOrdinate("10.99");
    private static final CoOrdinate LAT = new CoOrdinate("44.34");

    @Test
    void query() {
        assertTrue(!openWeatherHttpDataSource.makeHttpRequestToOpenWeatherApi(LONG, LAT).isEmpty());
    }
}