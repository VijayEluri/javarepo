/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets.models;

import com.mycompany.flight.passenger.models.Passenger;
import com.mycompany.flight.models.Flight;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author mreyesab
 */
@Entity
@Table (name="ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String status;
    
    private double price;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Passenger passenger;
    
    @ManyToOne
    private Flight outboundFlight;
    
    @ManyToOne
    private Flight returnFlight;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the outboundFlight
     */
    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    /**
     * @param outboundFlight the outboundFlight to set
     */
    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @return the returnFlight
     */
    public Flight getReturnFlight() {
        return returnFlight;
    }

    /**
     * @param returnFlight the returnFlight to set
     */
    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }
}
