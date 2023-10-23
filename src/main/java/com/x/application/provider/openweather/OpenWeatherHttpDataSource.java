package com.x.application.provider.openweather;

import com.x.application.CoOrdinate;
import com.x.application.provider.WeatherDataSource;
import com.x.domain.MetricResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
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

        WebClient webClient = WebClient.builder()
                .baseUrl(serviceURL)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        OpenWeatherClient openWeatherService = httpServiceProxyFactory.createClient(OpenWeatherClient.class);

        try {
            String strResponse = openWeatherService
                    .getForecast(latitude.toString(),longitude.toString(),apiKey);
            log.info(">> OpenWeather >>"+strResponse);
            return strResponse;
        }catch (Exception ex){
            log.error("Failed OpenWeather request. "+ex.getMessage());
            throw new RuntimeException("Failed when trying to query OpenWeather API",ex);
        }
    }
}
