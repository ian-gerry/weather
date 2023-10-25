package com.x.domain.ports;

import com.x.domain.model.Location;
import com.x.domain.model.MetricResult;
import com.x.weather.Metric;

import java.util.Optional;


public interface WeatherProviderPort {

    Optional<MetricResult> getMetric(Metric metric, int period, Location location);
}
