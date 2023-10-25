package com.x.weather.provider.openweather;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestParam;

interface OpenWeatherClient {

    @RequestLine("GET /data/2.5/forecast?lat={lat}&lon={lon}&appid={appid}")
    @Headers("Accept: application/json")
    String getForecast(@Param("lat") String lat, @Param("lon") String lon, @Param("appid") String appid);

}
