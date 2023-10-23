package com.x.application;


import com.x.domain.GeoLocationAdapter;
import com.x.domain.MetricResult;
import com.x.domain.WeatherAdapter;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.Instant;
import java.util.Optional;

@Controller("/api/v1/weather")
@Validated
public class WeatherEndpoint {

    private final WeatherAdapter weatherAdapter;
    private final GeoLocationAdapter geoLocationAdapter;


    public WeatherEndpoint(WeatherAdapter weatherAdapter, GeoLocationAdapter geoLocationAdapter){
        this.weatherAdapter=weatherAdapter;
        this.geoLocationAdapter=geoLocationAdapter;
    }

    @GetMapping("/location/{longitude}/{latitude}")
    public ResponseEntity<MetricResult> maxForPreviousPeriod(@NotBlank @PathParam("longitude") String longitudeString,
                                                       @NotBlank @PathParam("latitude") String latitudeString){
        try{
            CoOrdinate longitude = new CoOrdinate(longitudeString);
            CoOrdinate latitude =  new CoOrdinate(latitudeString);
            if(geoLocationAdapter.withinAllowedRegion(longitude,latitude)){
                ResponseEntity.badRequest().build();
            }

            return weatherAdapter.getMetric(Metric.MAX_TEMP,5, longitude,latitude)
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
