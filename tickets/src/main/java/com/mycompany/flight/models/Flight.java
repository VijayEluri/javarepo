/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.models;

import com.mycompany.flight.passenger.models.PassengerFlightDetails;
import com.mycompany.tickets.models.Ticket;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mreyesab
 */
@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int number;
    @OneToMany(mappedBy="flight", cascade={CascadeType.ALL})
    private Set<PassengerFlightDetails> passengerFlightDetails = new HashSet<PassengerFlightDetails>();
    
    @OneToMany //TODO (mappedBy="ticket", cascade={CascadeType.ALL})
    private Set<Ticket> tickets = new HashSet<Ticket>();

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ticket
     */
    public Set<Ticket> getTickets() {
        return tickets;
    }

    /**
     * @return the passengerFlightDetails
     */
    public Set<PassengerFlightDetails> getPassengerFlightDetails() {
        return passengerFlightDetails;
    }

    /**
     * @param ticket the ticket to set
     */
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public void addPassengerFlightDetails(PassengerFlightDetails passengerFlightDetails) {
        this.passengerFlightDetails.add(passengerFlightDetails);
    }

    public void removePassengerFlightDetails(PassengerFlightDetails passengerFlightDetails) {
        this.passengerFlightDetails.remove(passengerFlightDetails);
    }
}
