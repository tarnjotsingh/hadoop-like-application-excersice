package com.reading.gv009864.advancedcomputing.airline;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Flight {

    private String flightId;
    private String srcAirportCode;      // Airport IATA/FAA code
    private String destAiportCode;      // Airport IATA/FAA code
    private Date departTime;            // From epoch time
    private Long totalFlightTime;
    LinkedList<String> passengers;


    // Private default constructor
    private Flight() {}

    public Flight(String flightId, String srcAirportCode, String destAiportCode, Long departTime, Long totalFlightTime) {
        this.flightId = flightId;
        this.srcAirportCode = srcAirportCode;
        this.destAiportCode = destAiportCode;
        this.departTime = new Date(departTime * 1000);  // Convert epoch time to date object
        this.totalFlightTime = totalFlightTime;
        passengers = null;
    }






}
