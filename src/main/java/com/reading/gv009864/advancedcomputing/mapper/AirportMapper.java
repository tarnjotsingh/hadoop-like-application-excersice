package com.reading.gv009864.advancedcomputing.mapper;

import com.reading.gv009864.advancedcomputing.App;

import com.reading.gv009864.advancedcomputing.airline.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class AirportMapper extends Thread {
    private static Logger log = LoggerFactory.getLogger(AirportMapper.class);

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
            // Skip the line if somehow the data is not of the correct size.
            if(s.length != 4) {
                log.error("Loaded data entry is of incorrect size. Expected {}, found {}.", 4, s.length);
                return;
            }
            // First check if a flight KeyValue pair already exists.
            // If it does, ignore it because we cannot have duplicate airport entries.
            // Use the airport code as the key.
            if(this.hashMap.get(s[1]) == null){
                this.hashMap.put(
                        s[1],
                        new Airport(s[0], s[1], s[2], s[3])
                );
            }
        }
    }

    public HashMap<String, Airport> getHashMap() {
        return hashMap;
    }
}
