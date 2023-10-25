package com.x.weather.adapters.api;


import com.x.domain.GeoLocationAdapter;
import com.x.domain.ports.WeatherProviderPort;
import com.x.domain.model.Location;
import com.x.domain.model.MetricResult;
import com.x.weather.CoOrdinate;
import com.x.weather.Metric;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WeatherServiceApi {

    private final WeatherProviderPort weatherProviderPort;
    private final GeoLocationAdapter geoLocationAdapter;

    public WeatherServiceApi(WeatherProviderPort weatherProviderPort, GeoLocationAdapter geoLocationAdapter){
        this.weatherProviderPort = weatherProviderPort;
        this.geoLocationAdapter=geoLocationAdapter;
    }

    public Optional<MetricResult> getMetric(Metric metric, int period, CoOrdinate longitude, CoOrdinate latitude){
        Location location = new Location(longitude.toString(),latitude.toString());
        return weatherProviderPort.getMetric(metric,period,location);
    }

    public boolean withinAllowedRegion(CoOrdinate longitude, CoOrdinate latitude){
        Location location = new Location(longitude.toString(),latitude.toString());
        return geoLocationAdapter.withinAllowedRegion(location);
    }
}
