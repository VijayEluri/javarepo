/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets.models;

import com.mycompany.flight.models.OutboundFlight;
import com.mycompany.flight.passenger.models.Passenger;

/**
 *
 * @author mreyesab
 */
public class TicketMother {

    public static Ticket create() {
        
        OutboundFlight outboundFlight = new OutboundFlight();
        outboundFlight.setNumber(111);
        
        Ticket ticket = new Ticket();
        ticket.setStatus("pending");
        ticket.setOutboundFlight(outboundFlight);
        ticket.setPrice(1000.00);
        
        Passenger passenger = new Passenger();
        passenger.setFirstName("Miguel");
        passenger.setTicket(ticket);
        
        ticket.setPassenger(passenger);
        
        
        return ticket;
    }
    
}
