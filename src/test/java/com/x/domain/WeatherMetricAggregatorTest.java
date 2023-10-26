package com.x.domain;

import com.x.domain.model.MetricResult;
import com.x.weather.Metric;
import com.x.weather.provider.WeatherDataSourcePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherMetricAggregatorTest {

    private WeatherMetricAggregator weatherMetricAggregator;

    @Mock
    private WeatherDataSourcePort weatherDataSourcePort;
    @Mock
    private WarmestDayMetricRanking warmestDayMetricRanking;

    @Mock
    private RankingByHumidity rankingByHumidity;

    @BeforeEach
    void init(){
        weatherMetricAggregator = new WeatherMetricAggregator(weatherDataSourcePort,warmestDayMetricRanking, rankingByHumidity);
    }


    @Test
    void getMaxTempSingleTemp() {

        var metricResults = new ArrayList<MetricResult>();
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.MAX_TEMP, 10.0));

        when(warmestDayMetricRanking.rank(any()))
                .thenReturn(metricResults);

        assertEquals(10.0, weatherMetricAggregator.aggregateTempAndHumidityMetrics(metricResults).get().value());
    }
}