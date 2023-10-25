package com.x.domain.model;

import com.x.weather.Metric;

public record  MetricResult (long date, Metric metric, Double value){
}
