package com.x.weather.provider.openweather;

import com.x.domain.model.Location;
import com.x.weather.provider.WeatherDataSourcePort;
import com.x.domain.model.MetricResult;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class OpenWeatherHttpDataSourcePort implements WeatherDataSourcePort {

    private static Logger log = LoggerFactory.getLogger(OpenWeatherHttpDataSourcePort.class);

    @Value("${provider.openweather.apikey}")
    private String apiKey;

    @Value("${provider.openweather.serviceURL}")
    private String serviceURL;

    private final OpenWeatherResponseParser openWeatherResponseParser;


    public OpenWeatherHttpDataSourcePort(OpenWeatherResponseParser openWeatherResponseParser){
       this.openWeatherResponseParser = openWeatherResponseParser;
    }


    @Override
    public List<MetricResult> queryForDaily(Location location) {
        return openWeatherResponseParser.parse(makeHttpRequestToOpenWeatherApi(location));
    }

    public String makeHttpRequestToOpenWeatherApi(Location location){
        try {
            OpenWeatherClient openWeatherClient = Feign.builder()
                    .client(new OkHttpClient())
                    .target(OpenWeatherClient.class, serviceURL);
            return openWeatherClient
                    .getForecast(location.latitude(), location.longitude(), apiKey);
        }catch (Exception ex){
            log.error("Failed OpenWeather request. "+ex.getMessage());
            throw new RuntimeException("Failed when trying to query OpenWeather API",ex);
        }
    }
}
