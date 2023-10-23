package com.x.application.provider.openweather;

import com.x.application.provider.ProviderResponseParser;
import com.x.domain.MetricResult;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OpenWeatherResponseParser implements ProviderResponseParser {

    @Override
    public List<MetricResult> parse(String s) {
        return Collections.emptyList();
    }
}
