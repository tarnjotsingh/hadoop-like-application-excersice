package com.reading.gv009864.advancedcomputing.airline;

import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

public class Flight {

    private String id;
    private String srcAirportCode;      // Airport IATA/FAA code
    private String destAiportCode;      // Airport IATA/FAA code
    private Date departTime;            // From epoch time
    private Long totalFlightTime;
    private LinkedList<String> passengers;


    // Private default constructor
    private Flight() {}

    public Flight(String flightId, String srcAirportCode, String destAiportCode, String departTime, String totalFlightTime) {
        this.id = flightId;
        this.srcAirportCode = srcAirportCode;
        this.destAiportCode = destAiportCode;
        this.departTime = new Date(Long.parseLong(departTime) * 1000);  // Convert epoch time to date object
        this.totalFlightTime = Long.parseLong(totalFlightTime);
        passengers = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrcAirportCode() {
        return srcAirportCode;
    }

    public void setSrcAirportCode(String srcAirportCode) {
        this.srcAirportCode = srcAirportCode;
    }

    public String getDestAiportCode() {
        return destAiportCode;
    }

    public void setDestAiportCode(String destAiportCode) {
        this.destAiportCode = destAiportCode;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Long getTotalFlightTime() {
        return totalFlightTime;
    }

    public void setTotalFlightTime(Long totalFlightTime) {
        this.totalFlightTime = totalFlightTime;
    }

    public LinkedList<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(LinkedList<String> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(String passengerId) {
        this.passengers.add(passengerId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) &&
                Objects.equals(srcAirportCode, flight.srcAirportCode) &&
                Objects.equals(destAiportCode, flight.destAiportCode) &&
                Objects.equals(departTime, flight.departTime) &&
                Objects.equals(totalFlightTime, flight.totalFlightTime) &&
                Objects.equals(passengers, flight.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, srcAirportCode, destAiportCode, departTime, totalFlightTime, passengers);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", srcAirportCode='" + srcAirportCode + '\'' +
                ", destAiportCode='" + destAiportCode + '\'' +
                ", departTime=" + departTime +
                ", totalFlightTime=" + totalFlightTime +
                ", passengers=" + passengers +
                '}';
    }
}
