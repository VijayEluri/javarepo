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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param entityManager the em to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
