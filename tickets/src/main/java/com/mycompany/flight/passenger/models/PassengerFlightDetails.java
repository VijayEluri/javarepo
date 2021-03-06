/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.passenger.models;

import com.mycompany.flight.models.Flight;
import com.mycompany.flight.models.Seat;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author mreyesab
 */
@Entity
public class PassengerFlightDetails implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private Passenger passenger;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Seat seat;
    
    @ManyToOne
    private Flight flight;

    public PassengerFlightDetails() {
    }
    
    public PassengerFlightDetails(Seat seat) {
        this.seat = seat;
    }
    
    public PassengerFlightDetails(Flight flight, Seat seat) {
        this.seat = seat;
        this.flight = flight;
    }

    /**
     * @return the seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * @param passenger the passenger to set
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * @return the flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
