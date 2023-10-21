package domain;

import application.CoOrdinate;
import application.Metric;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface WeatherAdapter {

    Optional<String> getMetric(Metric metric, int period, CoOrdinate longitude, CoOrdinate latitude);
}
