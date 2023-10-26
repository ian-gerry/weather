package com.x.weather.provider.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.weather.Metric;
import com.x.weather.provider.ProviderResponseParser;
import com.x.domain.model.MetricResult;
import com.x.weather.provider.openweather.model.OpenWeatherDayItem;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenWeatherResponseParser implements ProviderResponseParser {

    private final ObjectMapper objectMapper;

    OpenWeatherResponseParser(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }

    @Override
    public List<MetricResult> parse(String json) {

        try {
            JsonNode jsonNodeRoot = objectMapper.readTree(json);
            return adaptToMetricResult(jsonNodeRoot);
        }catch(JsonProcessingException ex){
            throw new RuntimeException("Unable to parse provider (OpenWeather) response",ex);
        }
        catch(IOException ex){
            throw new RuntimeException("Unable to parse OpenWeather response, IO exception",ex);
        }

    }

    List<MetricResult> adaptToMetricResult(JsonNode jsonNodeRoot) throws IOException {
        JsonNode jsonNodeItems = jsonNodeRoot.get("list");
        List<OpenWeatherDayItem> items  = objectMapper
                .readerForListOf(OpenWeatherDayItem.class)
                .readValue(jsonNodeItems);
        return items.stream()
                .flatMap(ow -> {
                            var date = parseDate(ow.dt_txt());
                            var metrics = List.of(new MetricResult(date, ow.dt(),Metric.MAX_TEMP, ow.main().temp_max()),
                            new MetricResult(date,ow.dt(),Metric.HUMIDITY, ow.main().humidity()));
                    return metrics.stream();
                }
                )
                .collect(Collectors.toList());
    }

    private LocalDate parseDate(String s){
        String[] parts = s.split(" ");
        if(parts.length!=2){
            throw new RuntimeException("Provider has supplied badly formatted dates " + s);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(parts[0],formatter);
    }

}
