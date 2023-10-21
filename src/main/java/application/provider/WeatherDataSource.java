package application.provider;

import application.CoOrdinate;

public interface WeatherDataSource {

    String query(CoOrdinate longitude, CoOrdinate latitude);
}
