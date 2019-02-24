package com.reading.gv009864.advancedcomputing.mapper;

import com.reading.gv009864.advancedcomputing.App;

import com.reading.gv009864.advancedcomputing.airline.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class AirportMapper extends Thread {
    private static Logger log = LoggerFactory.getLogger(App.class);

    private List<String[]> airportData;
    private HashMap<String, Airport> hashMap;

    private AirportMapper() { }

    public AirportMapper(List<String[]> airportData) {
        this.airportData = airportData;
        this.hashMap = new HashMap<>();
    }

    @Override
    public void run() {
        super.run();
        for(String[] s : this.airportData) {
            // Kill the run job if somehow the data is not of the correct size.
            if(s.length != 6) {
                log.error("Loaded data entry is of incorrect size. Expected {}, found {}.", 4, this.airportData.size());
                return;
            }
            // First check if a flight KeyValue pair already exists.
            // If it does, ignore it because we cannot have duplicate airport entries.
            if(this.hashMap.get(s[0]) == null){
                this.hashMap.put(
                        s[0],
                        new Airport(s[0], s[1], s[2], s[3])
                );
            }
        }
    }

    public HashMap<String, Airport> getHashMap() {
        return hashMap;
    }
}
