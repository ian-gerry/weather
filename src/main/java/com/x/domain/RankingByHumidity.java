package com.x.domain;

import com.x.domain.model.MetricResult;
import com.x.weather.Metric;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
class RankingByHumidity implements MetricRanking {
    @Override
    public List<MetricResult> rank(List<MetricResult> metrics) {

        if(metrics==null || metrics.isEmpty()){
            return Collections.emptyList();
        }
        TreeMap<Double, List<MetricResult>> sortedByHumidity = metrics
                .stream()
                .filter(p-> Metric.HUMIDITY.equals(p.metric()))
                .collect(Collectors.groupingBy(
                        MetricResult::value,
                        TreeMap::new,
                        Collectors.toList()));

        Double lowest =  sortedByHumidity.descendingKeySet().last();
        return sortedByHumidity.get(lowest);
    }
}
