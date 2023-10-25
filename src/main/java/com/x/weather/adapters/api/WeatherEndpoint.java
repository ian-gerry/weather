package com.x.weather.adapters.api;


import com.x.domain.model.MetricResult;
import com.x.weather.CoOrdinate;
import com.x.weather.History;
import com.x.weather.Metric;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.Instant;

@Controller("/api/v1/weather")
@Validated
/**
 * Driver adapter for obtaining weather
 */
class WeatherEndpoint {

    private final WeatherServiceApi weatherServiceApi;

    WeatherEndpoint(WeatherServiceApi weatherServiceApi){
        this.weatherServiceApi=weatherServiceApi;
    }

    @GetMapping("/location/{longitude}/{latitude}")
    public ResponseEntity<MetricResult> maxForPreviousPeriod(@NotBlank @PathParam("longitude") String longitudeString,
                                                       @NotBlank @PathParam("latitude") String latitudeString){
        try{
            CoOrdinate longitude = new CoOrdinate(longitudeString);
            CoOrdinate latitude =  new CoOrdinate(latitudeString);
            if(weatherServiceApi.withinAllowedRegion(longitude,latitude)){
                ResponseEntity.badRequest().build();
            }

            return weatherServiceApi.getMetric(Metric.MAX_TEMP,5, longitude,latitude)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/history")
    public ResponseEntity<History> getRequestHistory(@RequestParam("ordering") String ordering, @RequestParam("max") int max){

        // TODO
        Instant now = Instant.now();
        var row =  new History(now,"x");
        return ResponseEntity.ok(row);
    }


}
