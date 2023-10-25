package com.x.weather.provider.openweather;

import com.x.weather.CoOrdinate;
import com.x.weather.provider.WeatherDataSource;
import com.x.domain.MetricResult;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.HashMap;
import java.util.List;



@Component
public class OpenWeatherHttpDataSource implements WeatherDataSource {

    private static Logger log = LoggerFactory.getLogger(OpenWeatherHttpDataSource.class);

    @Value("${provider.openweather.apikey}")
    private String apiKey;

    @Value("${provider.openweather.serviceURL}")
    private String serviceURL;

    private final OpenWeatherResponseParser openWeatherResponseParser;


    public OpenWeatherHttpDataSource(OpenWeatherResponseParser openWeatherResponseParser){
       this.openWeatherResponseParser = openWeatherResponseParser;
    }


    @Override
    public List<MetricResult> queryForDaily(CoOrdinate longitude, CoOrdinate latitude) {
        return openWeatherResponseParser.parse(makeHttpRequestToOpenWeatherApi(longitude,latitude));
    }

    protected String makeHttpRequestToOpenWeatherApi(CoOrdinate longitude, CoOrdinate latitude){
        try {
            OpenWeatherClient openWeatherClient = Feign.builder()
                    .client(new OkHttpClient())
                    .target(OpenWeatherClient.class, serviceURL);
            OpenWeatherSummary weatherSummary = openWeatherClient
                    .getForecast(latitude.toString(),longitude.toString(),apiKey);

            return "to do";
        }catch (Exception ex){
            log.error("Failed OpenWeather request. "+ex.getMessage());
            throw new RuntimeException("Failed when trying to query OpenWeather API",ex);
        }
    }
}
