package com.x.domain.model;

import com.x.weather.Metric;

import java.time.LocalDate;

public record  MetricResult (LocalDate dateUTC, long time, Metric metric, Double value){
}
