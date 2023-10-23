package com.x.domain;

import com.x.application.CoOrdinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationAdapter {
    private static Logger log = LoggerFactory.getLogger(GeoLocationAdapter.class);
    public boolean withinAllowedRegion(CoOrdinate longitude, CoOrdinate latitude){
        log.warn("Geo location fencing not implemented yet");
        return true;   // TODO:  To be implemented
    }
}
