/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.passenger.models;

import com.mycompany.flight.models.Flight;
import com.mycompany.flight.models.OutboundFlight;
import com.mycompany.flight.models.ReturnFlight;
import com.mycompany.tickets.models.Ticket;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author mreyesab
 */
@Entity
public class Passenger implements Serializable {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    @OneToOne
    private Ticket ticket;
    
    @OneToMany(mappedBy="passenger", cascade = CascadeType.ALL)
    private Set<PassengerFlightDetails> passengerFlightDetails = new HashSet<PassengerFlightDetails>();

    public PassengerFlightDetails getDetailsForFlight(Flight flight) {
        PassengerFlightDetails passengerFlightDetails = null;
        Flight myflight;
        if (ticket != null) {
            myflight = ticket.getOutboundFlight();
            if (myflight.getNumber() == flight.getNumber()) {
                //TODO implement
                //passengerFlightDetails = outboundFlight.getTicket().getPassenger().getPassengerFlightDetails();
            }
            myflight = ticket.getReturnFlight();
            if (myflight.getNumber() == flight.getNumber()) {
                //TODO implement
            }
        } else {
            System.out.println("No ticket available");
            return null;
        }
        return passengerFlightDetails; //TODO
    }
    
    public void addPassengerFlightDetails(PassengerFlightDetails passengerFlightDetails) {
        this.passengerFlightDetails.add(passengerFlightDetails);
    }
    
    public void removePassengerFlightDetails(PassengerFlightDetails passengerFlightDetails) {
        this.passengerFlightDetails.remove(passengerFlightDetails);
    }

    public void setFlightDetails(Flight flight, PassengerFlightDetails passengerFlightDetails) throws Exception {
        if (flight != null) {            
            if (ticket == null) {
                System.out.println("Error: No ticket has been set!");
                throw new Exception("No ticket has been set!");
            }
            this.passengerFlightDetails.add(passengerFlightDetails);
            
            if (flight instanceof OutboundFlight) {
                ticket.setOutboundFlight((OutboundFlight) flight);
            } else if (flight instanceof ReturnFlight) {
                ticket.setReturnFlight((ReturnFlight) flight);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the ticket
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
