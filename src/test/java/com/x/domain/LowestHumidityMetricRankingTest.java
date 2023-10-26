package com.x.domain;

import com.x.domain.model.MetricResult;
import com.x.weather.Metric;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LowestHumidityMetricRankingTest {

    private RankingByHumidity ranking = new RankingByHumidity();

    private final static double MIN_HUMIDITY = 1.0;

    @Test
    void emptyMetricsRank() {
        assertNotNull(ranking.rank(Collections.emptyList()));
    }

    @Test
    void differentHumidityOnDistinctDaysGivesMinimum(){
        List<MetricResult> metric = ranking.rank(createHumidityOnDistinctDays());
        assertEquals(1,metric.size());
        assertEquals(MIN_HUMIDITY,metric.get(0).value());
    }


    @Test
    List<MetricResult> createHumidityOnDistinctDays(){
        var metricResults = new ArrayList<MetricResult>();
        metricResults.add(new MetricResult(LocalDate.now().minusDays(2),1698256800, Metric.HUMIDITY, MIN_HUMIDITY+2));
        metricResults.add(new MetricResult(LocalDate.now().minusDays(1),1698256800, Metric.HUMIDITY, MIN_HUMIDITY+1));
        metricResults.add(new MetricResult(LocalDate.now(),1698256800, Metric.HUMIDITY, MIN_HUMIDITY));
        return metricResults;
    }
}