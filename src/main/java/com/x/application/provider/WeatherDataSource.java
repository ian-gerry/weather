package com.x.application.provider;

import com.x.application.CoOrdinate;
import com.x.application.Metric;
import com.x.domain.MetricResult;

import java.util.List;

public interface WeatherDataSource {

    List<MetricResult> queryForDaily(CoOrdinate longitude, CoOrdinate latitude);
}
