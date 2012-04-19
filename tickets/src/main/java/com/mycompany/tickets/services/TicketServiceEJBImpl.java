/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets.services;

import com.mycompany.tickets.models.Ticket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mreyesab
 */
@Stateless
public class TicketServiceEJBImpl implements TicketService {

    @PersistenceContext(unitName = "flightdb")
    private EntityManager entityManager;
    
    @Override
    public Ticket save(Ticket ticket) {
        entityManager.persist(ticket);

        return ticket;
    }

    @Override
    public Ticket findById(long id) {
        Ticket ticket = entityManager.find(Ticket.class, new Long(id));
        return ticket;
    }

    /**
     * @param entityManager the em to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void delete(Ticket ticket) {
        this.entityManager.remove(ticket);
    }
}
