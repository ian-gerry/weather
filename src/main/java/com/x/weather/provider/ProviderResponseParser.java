package com.x.weather.provider;

import com.x.domain.MetricResult;

import java.util.List;

public interface ProviderResponseParser {

    List<MetricResult> parse(String s);
}
