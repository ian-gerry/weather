package com.x.domain;

import com.x.domain.model.MetricResult;
import com.x.weather.Metric;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WarmestDayMetricRankingTest {

    private WarmestDayMetricRanking ranking = new WarmestDayMetricRanking();

    private final static double MAX_TEMP = 20.0;

    @Test
    void emptyMetricsRank() {
        assertNotNull(ranking.rank(Collections.emptyList()));
    }

    @Test
    void differentTemperaturesOnSameDay(){
        List<MetricResult> metric = ranking.rank(twoDifferentMaxTemps());
        assertEquals(1,metric.size());
        assertTrue(metric.get(0).value().equals(MAX_TEMP));
    }

    @Test
    void equalMaxTemperaturesOnDifferentDaysGivesBothDays(){
        List<MetricResult> metric = ranking.rank(createTwoEqualMaxTempsOnDistinctDays());
        assertEquals(2,metric.size());
        assertTrue(metric.get(0).value().equals(MAX_TEMP));
        assertTrue(metric.get(1).value().equals(MAX_TEMP));
    }

    @Test
    List<MetricResult> twoDifferentMaxTemps(){
        var metricResults = new ArrayList<MetricResult>();
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.MAX_TEMP, 10.0));
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.HUMIDITY, 2.0));
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.MAX_TEMP, MAX_TEMP));
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.HUMIDITY, 1.0));
        return metricResults;
    }

    @Test
    List<MetricResult> createTwoEqualMaxTempsOnDistinctDays(){
        var metricResults = new ArrayList<MetricResult>();
        metricResults.add(new MetricResult(LocalDate.now().minusDays(2),1698256800, Metric.MAX_TEMP, 19.0));
        metricResults.add(new MetricResult(LocalDate.now().minusDays(1),1698256800, Metric.MAX_TEMP, MAX_TEMP));
        metricResults.add(new MetricResult(LocalDate.now().minusDays(1),1698256800, Metric.HUMIDITY, 2.0));
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.MAX_TEMP, MAX_TEMP));

        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.HUMIDITY, 1.0));
        return metricResults;
    }
}