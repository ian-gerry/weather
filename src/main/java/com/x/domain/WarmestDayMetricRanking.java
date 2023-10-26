package com.x.domain;

import com.x.domain.model.MetricResult;
import com.x.weather.Metric;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Component
class WarmestDayMetricRanking implements MetricRanking {
    @Override
    /** Return metrics associated with the warmest days.  If more than one day
     * share the same max temperature, - both MetricResult are returned */
    public List<MetricResult> rank(List<MetricResult> metrics) {

        if(metrics==null || metrics.isEmpty()){
            return Collections.emptyList();
        }
        TreeMap<Double, List<MetricResult>> maxTempMap = metrics
                .stream()
                .filter(p->Metric.MAX_TEMP.equals(p.metric()))
                .collect(Collectors.groupingBy(
                        MetricResult::value,
                        TreeMap::new,
                        Collectors.toList()));


        Double max = maxTempMap.descendingKeySet().first();
        List<MetricResult> maximumTemperatures = maxTempMap.get(max);
        boolean equalTempOnDistinctDays = maximumTemperatures.size()>1;
        return maximumTemperatures;
    }
}
