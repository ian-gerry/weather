package com.x.weather.provider;

import com.x.domain.model.MetricResult;

import java.util.List;

public interface ProviderResponseParser {

    /**
     * Convert provider supplied payload String into a collection of Metric results
     */
    List<MetricResult> parse(String s);
}
