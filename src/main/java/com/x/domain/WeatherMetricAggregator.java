package com.x.domain;

import com.x.domain.model.Location;
import com.x.domain.model.MetricResult;
import com.x.domain.ports.WeatherProviderPort;
import com.x.weather.Metric;
import com.x.weather.provider.WeatherDataSourcePort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WeatherMetricAggregator implements WeatherProviderPort {

    private final WeatherDataSourcePort weatherDataSourcePort;
    private final WarmestDayMetricRanking warmestDayMetricRanking;
    private final RankingByHumidity rankingByHumidity;

    WeatherMetricAggregator(WeatherDataSourcePort weatherDataSourcePort,
                                   WarmestDayMetricRanking warmestDayMetricRanking,
                                   RankingByHumidity rankingByHumidity){
        this.weatherDataSourcePort = weatherDataSourcePort;
        this.warmestDayMetricRanking=warmestDayMetricRanking;
        this.rankingByHumidity=rankingByHumidity;
    }


    @Override
    public Optional<MetricResult> getMetric(Metric metric, int period, Location location) {
        if (Objects.requireNonNull(metric) == Metric.MAX_TEMP) {
            return aggregateTempAndHumidityMetrics(weatherDataSourcePort.queryForDaily(location));
        }
        throw new UnsupportedOperationException("Other metrics not yet implemented");
    }

    /**
     * warmest day over the next 5 days.
     * Firstly. rank weather observations by temperature and secondly by humidity
     */
    Optional<MetricResult> aggregateTempAndHumidityMetrics(List<MetricResult> metricResults){

        List<MetricResult> rankedByTemp = warmestDayMetricRanking.rank(metricResults);
        if(rankedByTemp.size()>1){    // Further ranking by descending humidity required
            Set<LocalDate> equalTempDays =
                    rankedByTemp.stream().map(MetricResult::dateUTC).collect(Collectors.toSet());

            List<MetricResult> rankingRequired = metricResults.stream()
                    .filter(m-> equalTempDays.contains(m.dateUTC()))
                    .toList();

            List<MetricResult> rankedByHumidity =  rankingByHumidity
                    .rank(rankingRequired);

            LocalDate dateWithLowestHumidity = rankedByHumidity.get(0).dateUTC();
            return rankedByTemp.stream()
                    .filter(m->dateWithLowestHumidity.equals(m.dateUTC()))
                    .filter(m->m.metric().equals(Metric.MAX_TEMP))
                    .findFirst();

        }else{
            return Optional.of(rankedByTemp.get(0));
        }

    }
}
