/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.passenger.services;

import com.mycompany.flight.models.Flight;
import com.mycompany.flight.models.Seat;
import com.mycompany.flight.passenger.models.Passenger;
import com.mycompany.tickets.models.Ticket;
import java.util.Set;

/**
 *
 * @author miguel
 */
public interface PassengerServices {

    Set<Flight> findFlights(Passenger passenger);

    Set<Ticket> findTickets(Passenger passenger);

    Seat findSeatInFlight(Passenger passenger, Flight flight);
}
