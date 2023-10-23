package com.x.domain;

import com.x.application.CoOrdinate;
import com.x.application.Metric;

import java.util.Optional;


public interface WeatherAdapter {

    Optional<MetricResult> getMetric(Metric metric, int period, CoOrdinate longitude, CoOrdinate latitude);
}
