package com.x.domain;

import com.x.application.CoOrdinate;
import com.x.application.Metric;
import com.x.application.provider.WeatherDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WeatherMetricAggregator implements WeatherAdapter {

    private final WeatherDataSource weatherDataSource;

    public WeatherMetricAggregator(WeatherDataSource weatherDataSource){
        this.weatherDataSource = weatherDataSource;
    }


    @Override
    public Optional<MetricResult> getMetric(Metric metric, int period, CoOrdinate longitude, CoOrdinate latitude) {
        if (Objects.requireNonNull(metric) == Metric.MAX_TEMP) {
            return getMaxTempFromProvider(period, longitude, latitude);
        }
        throw new UnsupportedOperationException("Other metrics not yet implemented");
    }

    /**
     * warmest day over the next 5 days.
     */

    Optional<MetricResult> getMaxTempFromProvider(int period, CoOrdinate longitude, CoOrdinate latitude){

         List<MetricResult> metricResults =  weatherDataSource.queryForDaily(longitude,latitude);


         return Optional.empty();  //TODO:
    }
}
