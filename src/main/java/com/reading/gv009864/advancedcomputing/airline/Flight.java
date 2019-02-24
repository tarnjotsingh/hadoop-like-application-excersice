package com.reading.gv009864.advancedcomputing.airline;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Flight {

    private String id;
    private String srcAirportCode;      // Airport IATA/FAA code
    private String destAirportCode;      // Airport IATA/FAA code
    private Date departTime;            // From epoch time
    private SimpleDateFormat dateFormat;
    private Long totalFlightTime;
    private LinkedList<String> passengers;


    // Private default constructor
    private Flight() {}

    public Flight(String flightId, String srcAirportCode, String destAirportCode, String departTime, String totalFlightTime) {
        this.id = flightId;
        this.srcAirportCode = srcAirportCode;
        this.destAirportCode = destAirportCode;
        this.departTime = new Date(Long.parseLong(departTime) * 1000);  // Convert epoch time to date object
        this.dateFormat= new SimpleDateFormat("kk:mm:ss");
        this.totalFlightTime = Long.parseLong(totalFlightTime);
        passengers = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public String getSrcAirportCode() {
        return srcAirportCode;
    }

    public String getDestAirportCode() {
        return destAirportCode;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public String getFormattedDepartTime() {
        return this.dateFormat.format(this.departTime);
    }

    public Long getTotalFlightTime() {
        return totalFlightTime;
    }

    public LinkedList<String> getPassengers() {
        return passengers;
    }

    public void addPassenger(String passengerId) {
        this.passengers.add(passengerId);
    }

    public void addPassengers(List<String> passengers) {
        this.passengers.addAll(passengers);
    }

    public Flight mergePassengers(List<String> passengers) {
        this.addPassengers(passengers);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(srcAirportCode, flight.srcAirportCode) &&
                Objects.equals(destAirportCode, flight.destAirportCode) &&
                Objects.equals(departTime, flight.departTime) &&
                Objects.equals(totalFlightTime, flight.totalFlightTime) &&
                Objects.equals(passengers, flight.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, srcAirportCode, destAirportCode, departTime, totalFlightTime, passengers);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", srcAirportCode='" + srcAirportCode + '\'' +
                ", destAirportCode='" + destAirportCode + '\'' +
                ", departTime=" + this.getFormattedDepartTime() +
                ", totalFlightTime=" + totalFlightTime +
                '}';
    }
}
