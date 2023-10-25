package com.x.weather.provider.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherMainItem(Double temp_min, Double temp_max, Double humidity) {
}
