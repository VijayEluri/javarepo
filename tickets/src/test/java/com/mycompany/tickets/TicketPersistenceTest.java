/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets;

import com.mycompany.flight.models.Flight;
import com.mycompany.flight.services.FlightServiceEJBDImpl;
import com.mycompany.flight.passenger.models.PassengerFlightDetails;
import com.mycompany.flight.models.Seat;
import com.mycompany.tickets.services.TicketServiceEJBImpl;
import com.mycompany.tickets.models.Ticket;
import com.mycompany.tickets.models.TicketMother;
import com.mycompany.utils.InMemEntityManagerGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author mreyesab
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketPersistenceTest {
    
    private TicketServiceEJBImpl ticketDAO;
    private EntityManager entityManager;
    
    @Before
    public void setup(){
        InMemEntityManagerGenerator entityManagerGenerator = new InMemEntityManagerGenerator();
        entityManager = entityManagerGenerator.getEntityManager();
        
        ticketDAO = new TicketServiceEJBImpl();
        ticketDAO.setEntityManager(entityManager);
        
        System.out.println("### Before");
    }
    
    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
    }

   
    @Test
    public void testSave() {
        System.out.println("### Test");
        Ticket ticket = TicketMother.create();
        
        Flight outboundFlight = FlightServiceEJBDImpl.findById(1);
        Flight returnFlight = FlightServiceEJBDImpl.findById(2);

        ticket.setOutboundFlight(outboundFlight);
        ticket.setReturnFlight(returnFlight);
        
        try {
            ticket.getPassenger().setFlightDetails(outboundFlight, new PassengerFlightDetails(getSeatForFlight(outboundFlight, "2A")));
            ticket.getPassenger().setFlightDetails(returnFlight, new PassengerFlightDetails(getSeatForFlight(returnFlight, "2B")));
        } catch (Exception ex) {
            Logger.getLogger(TicketPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        ticketDAO.save(ticket);

        ticket = ticketDAO.findById(ticket.getId());
        
        Assert.assertEquals("Miguel", ticket.getPassenger().getFirstName());
        Assert.assertEquals("2A", ticket.getPassenger().getDetailsForFlight(ticket.getOutboundFlight()).getSeat().getNumber());
    }

    private Seat getSeatForFlight(Flight flight, String seatNumber) {
        return new Seat(seatNumber);
    }
}
