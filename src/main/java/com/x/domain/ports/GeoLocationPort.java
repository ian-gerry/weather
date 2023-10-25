package com.x.domain.ports;

import com.x.domain.model.Location;
import com.x.weather.CoOrdinate;

public interface GeoLocationPort {
    boolean withinAllowedRegion(Location location);
}
