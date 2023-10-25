package com.x.weather.provider.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

interface OpenWeatherSummary{
   @JsonProperty("list")
   List<WeatherItem> weatherItems();
}
