package com.reading.gv009864.advancedcomputing.reducer;

import com.reading.gv009864.advancedcomputing.airline.Airport;
import com.reading.gv009864.advancedcomputing.airline.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This Reducer class has the responsibility of resolving final values from
 * the passenger HashMap which contains those KeyValue pairs.
 *
 * The Airport data will also need to be used to calculate some values.
 */
public class Reducer {
    private Logger log = LoggerFactory.getLogger(Reducer.class);

    private HashMap<String, Flight> passengerMap;
    private HashMap<String, Airport> airportHashMap;
    private LinkedHashMap<String, Double> flightDistances;    // Create a new linked hash map to store the calculated distances

    private Reducer() { }

    public Reducer(HashMap<String, Flight> passengerMap
            , HashMap<String, Airport> airportHashMap) {
        this.passengerMap = passengerMap;
        this.airportHashMap = airportHashMap;
        this.flightDistances = new LinkedHashMap<>();   // Linked hash map lets the order be maintaned
    }

    /**
     * To check which airports have and have not been used, can iterate through each
     * element of the passenger HashMap and then use the airport key to find out if
     * the airport has been used. Increment the in-object value to signify this.
     *
     * @return String listing each airport code with the number of times they were used as a
     * source airport for a flight. This means even if they were used as a destination, the value
     * cannot be increased.
     */
    public String getSrcAirportsUsed() {
        log.info("Getting number of times an airport was used as a source.");
        StringBuilder builder = new StringBuilder();

        /*
         * An assumption being made here is that values in the passenger hash map use a
         * subset of the values in the airport hash map.
         *
         * Therefore if an airport is not found, an error will have to be thrown since the
         * data for that airport is not available.
         */
        this.passengerMap.forEach(
                (key, value) -> {
                    try {
                        airportHashMap.get(value.getSrcAirportCode()).incrementNumOfFlightsFrom();
                    }
                    catch(Exception e) {
                        log.error(e.getLocalizedMessage());
                        log.error(value.getSrcAirportCode() + " airport not found.");
                    }
                }
        );

        this.airportHashMap.forEach(
                (key, value) -> builder.append(key + " : " + value.getNumOfFlightsFrom() + System.lineSeparator())
        );

        return builder.toString();
    }

    /**
     * Create a list of flights based on the Flight id, this output should include
     * the passenger Id, relevant IATA/FAA codes, the departure time,
     * the arrival time (times to be converted to HH:MM:SS format), and the flight times.
     *
     * @return
     */
    public String getListOfFlights() {
        log.info("Getting list of flights.");
        StringBuilder builder = new StringBuilder();

        log.info("Processing flight information list...");

        this.passengerMap.forEach(
                (key, value) -> builder.append(value.toString() + System.lineSeparator())
        );

        return builder.toString();
    }

    /**
     * Simple method that will use the in-build forEach method in HashMap to
     * produce a string that can display the Key (flight id) and the number of
     * passengers for each flight.
     *
     * @return String that can be printed out to show the values for each flight.
     */
    public String getNumOfPassengersForEachFlight() {
        log.info("Getting number of passengers for each flight.");
        StringBuilder builder = new StringBuilder();

        log.info("Processing number of passengers...");

        this.passengerMap.forEach(
                (key, value) -> builder.append(
                        key + " : " + value.getPassengers().size() + System.lineSeparator())
        );

        return builder.toString();
    }

    /**
     * Will return a list of nautical miles covered by each flight
     *
     * @return String detailing the nautical miles traveled by each flight.
     */
    public String getDistances() {
        log.info("Getting distance (in nautical miles) covered by each flight.");
        StringBuilder builder = new StringBuilder();
        // Calculate the distances
        this.calculateDistances();
        this.sortDistances();


        this.flightDistances.forEach(
                (key, value) -> {
                    Flight f = this.passengerMap.get(key);
                    builder.append(
                            "FlightId: " + key + System.lineSeparator() +
                            "Nautical Miles: " + value + System.lineSeparator() +
                            "Passengers: " + System.lineSeparator() +
                                    f.getPassengers().stream()
                                            .collect(Collectors.joining("\n")) + System.lineSeparator() +
                            System.lineSeparator()
                    );
                }
        );


        return builder.toString();
    }


    public void calculateDistances() {
        this.passengerMap.forEach(
                (key, value) -> {
                    Airport a = this.airportHashMap.get(value.getSrcAirportCode());
                    Airport b = this.airportHashMap.get(value.getDestAirportCode());
                    this.flightDistances.put(
                            key,
                            distance(
                                    a.getLatitude(), a.getLongitude(),
                                    b.getLatitude(), b.getLongitude(),
                                    "N"
                            )
                    );
                }
        );

        // Sort the HashMap by value in ascending order
    }

    private void sortDistances() {
        // Produce a stream from the current entry set to run sorted and collect
        // toMap lets us create a new LinkedHashMap from the existing data.
        // Merge method doesn't need to anything since there won't be two values with the same key
        LinkedHashMap<String, Double> result = this.flightDistances.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        this.flightDistances = result;
    }

    /**
     * Code from : https://www.geodatasource.com/developers/java
     *
     * Calculate the distance traveled by each flight.
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @param unit
     * @return
     */
    private Double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        // No point in continuing if the two values pairs are the same
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0.0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit == "K") {
                dist = dist * 1.609344;
            } else if (unit == "N") {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
}
