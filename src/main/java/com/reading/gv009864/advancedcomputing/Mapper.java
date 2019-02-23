package com.reading.gv009864.advancedcomputing;

import com.reading.gv009864.advancedcomputing.airline.Flight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Mapper class which is threaded to run map jobs
 * in parallel to one another.
 * Will produce a Hash map of KeyValue pairs.
 *
 */
public class Mapper extends Thread {
    private Logger log = LoggerFactory.getLogger(Mapper.class);

    private List<String[]> data;
    private HashMap<String, Flight> hashMap;    // KeyValue pair of HashMap<FlightId, Flight>

    public Mapper(List<String[]> data) {
        this.data = data;
        this.hashMap = new HashMap<>();
    }

    public HashMap<String, Flight> getHashMap() {
        return hashMap;
    }

    @Override
    public void run() {
        super.run();
        /*
            Want to create this hash map between a String value and the rest of the data.
            This mapper has the aim of processing the passenger data to create those
            key value pairs.

            From the passenger data there is the passengerId along with the rest of the flight
            details. What can be seen is that because of the way the data is structured. Every
            Flight has a set of details and a NUMBER of passengers.

            A flight class should therefore be used to store the data to be processed.
            Every FLIGHT object should store the details and a LIST of passengers.

            PassengerID  FlightID  FROM TO  Departure Time  Duration
            BWI0520BG1,  RUM0422W, MUC, MAD,1420563539,     194

            Hash map is good choice here since it can let us find an existing instance of a FLIGHT
            and just add a new passenger to it.

            I guess based on this logic the KeyValue pair should be something like
            HashMap<flightId, Flight>
         */

        // Want to iterate through all elements, easier to just do a foreach
        for(String[] s : this.data) {
            // Kill the run job if somehow the data is not of the correct size.
            if(s.length != 6) {
                log.error("Loaded data entry is of incorrect size. Expected {}, found {}.", 6, this.data.size());
                return;
            }
            // First check if a flight KeyValue pair already exists.
            // If it does, just add a passenger to it.
            if(this.hashMap.get(s[1]) != null){
                this.hashMap.get(s[1]).addPassenger(s[0]);
            }
            // Otherwise add a new KeyValue pair to the HashMap as <FlightId, Flight>.
            else {
                this.hashMap.put(
                        s[1],
                        new Flight(s[1], s[2], s[3], s[4], s[5])
                );
                // And add the passenger >_>
                this.hashMap.get(s[1]).addPassenger(s[0]);
            }
        }
    }
}
