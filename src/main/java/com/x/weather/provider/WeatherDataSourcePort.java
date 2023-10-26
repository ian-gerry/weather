package com.x.weather.provider;

import com.x.domain.model.Location;
import com.x.domain.model.MetricResult;

import java.util.List;

public interface WeatherDataSourcePort {

    List<MetricResult> queryForDaily(Location location);
}
