package com.x.application.provider.openweather;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;


public interface OpenWeatherClient {

    @GetExchange("api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}")
    String getForecast(@RequestParam("lat") String lat, @RequestParam("lon") String lon, @RequestParam("appId") String appid);

}
