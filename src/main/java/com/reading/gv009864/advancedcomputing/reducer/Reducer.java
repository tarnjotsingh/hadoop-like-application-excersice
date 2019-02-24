package com.reading.gv009864.advancedcomputing.reducer;

import com.reading.gv009864.advancedcomputing.airline.Airport;
import com.reading.gv009864.advancedcomputing.airline.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

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

    private Reducer() { }

    public Reducer(HashMap<String, Flight> passengerMap
            , HashMap<String, Airport> airportHashMap) {
        this.passengerMap = passengerMap;
    }

    /**
     * To check which airports have and have not been used
     * the passengerM
     * @return
     */
    public String getAirportsUsed() {
        return "";
    }

    /**
     * Create a list of flights based on the Flight id, this output should include
     * the passenger Id, relevant IATA/FAA codes, the departure time,
     * the arrival time (times to be converted to HH:MM:SS format), and the flight times.
     *
     * @return
     */
    public String getListOfFlights() {
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
        StringBuilder builder = new StringBuilder();

        log.info("Processing number of passengers...");

        this.passengerMap.forEach(
                (key, value) -> builder.append(
                        key + " : " + value.getPassengers().size() + System.lineSeparator())
        );

        return builder.toString();
    }



}
