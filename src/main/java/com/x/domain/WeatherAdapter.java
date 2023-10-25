package com.x.domain;

import com.x.weather.CoOrdinate;
import com.x.weather.Metric;

import java.util.Optional;


public interface WeatherAdapter {

    Optional<MetricResult> getMetric(Metric metric, int period, CoOrdinate longitude, CoOrdinate latitude);
}
