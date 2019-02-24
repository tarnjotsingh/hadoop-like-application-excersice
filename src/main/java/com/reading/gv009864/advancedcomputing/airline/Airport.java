package com.reading.gv009864.advancedcomputing.airline;

import com.reading.gv009864.advancedcomputing.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class for storing airport data that was parsed from
 * the airports.csv file.
 */
public class Airport {
    private static Logger log = LoggerFactory.getLogger(App.class);

    private String airportName;
    private String airportCode;
    private Double latitude;
    private Double longitude;

    private Airport() { }

    public Airport(String airportName, String airportCode, String latitude, String longitude) {
        this.airportCode = airportName;
        this.airportCode = airportCode;

        // Try to parse the values, if they fail then just default them to 0 and log the error.
        try {
            this.latitude = Double.parseDouble(latitude);
            this.longitude = Double.parseDouble(longitude);
        }
        catch (Exception e) {
            this.latitude = 0.0;
            this.longitude = 0.0;
            log.error(e.getLocalizedMessage());
        }
    }

    public static Logger getLog() {
        return log;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportName='" + airportName + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
