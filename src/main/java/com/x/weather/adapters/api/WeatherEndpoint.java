package com.x.weather.adapters.api;


import com.x.domain.model.MetricResult;
import com.x.weather.CoOrdinate;
import com.x.weather.History;
import com.x.weather.Metric;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;


@RestController
@RequestMapping("/api/v1/weather")
@Validated
/**
 * Driver adapter for obtaining weather
 */
public class WeatherEndpoint {

    private final WeatherServiceApi weatherServiceApi;

    WeatherEndpoint(WeatherServiceApi weatherServiceApi){
        this.weatherServiceApi=weatherServiceApi;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return
                ResponseEntity.status(HttpStatus.OK)
                        .body("ok");
    }


    @GetMapping("/location/{latitude}/{longitude}")
    public ResponseEntity<MetricResult> maxTemperatureForPeriod(
            @NotBlank @PathVariable("latitude") String latitudeString,
            @NotBlank @PathVariable("longitude") String longitudeString) {

        try {
            CoOrdinate longitude = new CoOrdinate(longitudeString);
            CoOrdinate latitude = new CoOrdinate(latitudeString);
            if (!weatherServiceApi.withinAllowedRegion(longitude, latitude)) {
                ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .build();
            }

            return weatherServiceApi.getMetric(Metric.MAX_TEMP, 5, longitude, latitude)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .build();
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
