package com.x.domain;

import com.x.domain.model.Location;
import com.x.domain.model.MetricResult;
import com.x.domain.ports.WeatherProviderPort;
import com.x.weather.Metric;
import com.x.weather.provider.WeatherDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WeatherMetricAggregator implements WeatherProviderPort {

    private final WeatherDataSource weatherDataSource;

    public WeatherMetricAggregator(WeatherDataSource weatherDataSource){
        this.weatherDataSource = weatherDataSource;
    }


    @Override
    public Optional<MetricResult> getMetric(Metric metric, int period, Location location) {
        if (Objects.requireNonNull(metric) == Metric.MAX_TEMP) {
            return getMaxTempFromProvider(period,location);
        }
        throw new UnsupportedOperationException("Other metrics not yet implemented");
    }

    /**
     * warmest day over the next 5 days.
     */
    Optional<MetricResult> getMaxTempFromProvider(int period, Location location){

         List<MetricResult> metricResults =  weatherDataSource.queryForDaily(location);


         return Optional.empty();  //TODO:
    }
}
