/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets.services;

import com.mycompany.tickets.models.Ticket;

/**
 *
 * @author mreyesab
 */
public interface TicketService {
    
    public Ticket save(Ticket ticket);

    public Ticket findById(long id);
    
    public void delete(Ticket ticket);
    
}
