package com.x.domain;

import com.x.domain.model.Location;
import com.x.domain.ports.GeoLocationPort;
import com.x.weather.CoOrdinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationAdapter implements GeoLocationPort {
    private static Logger log = LoggerFactory.getLogger(GeoLocationAdapter.class);

    @Override
    public boolean withinAllowedRegion(Location location){
        log.warn("Geo location fencing not implemented yet");
        return true;   // TODO:  To be implemented
    }
}
