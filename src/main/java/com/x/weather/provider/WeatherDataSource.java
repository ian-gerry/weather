package com.x.weather.provider;

import com.x.weather.CoOrdinate;
import com.x.domain.MetricResult;

import java.util.List;

public interface WeatherDataSource {

    List<MetricResult> queryForDaily(CoOrdinate longitude, CoOrdinate latitude);
}
