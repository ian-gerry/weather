package com.x.weather.provider.openweather.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherDayItem(long dt, String dt_txt, OpenWeatherMainItem main) {


}
