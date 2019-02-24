package com.reading.gv009864.advancedcomputing.airline;

/**
 * Wrapper class to just store a flight and it's distance together.
 * Did not think it would be a good design choice to store the distance
 * in the flight class, so this wrapper class is made to solve that.
 */
public class FlightDistance {
    private Flight flight;
    private Double distance;

    public FlightDistance(Flight flight, Double distance) {
        this.flight = flight;
        this.distance = distance;
    }

    public Flight getFlight() {
        return flight;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "FlightDistance{" +
                "flightID=" + flight.getId() +
                ", distance=" + distance +
                ", passengers=" + flight.getPassengers()+
                '}';
    }
}
