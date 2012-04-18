/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.services;

import com.mycompany.flight.models.Flight;
import java.util.Random;

/**
 *
 * @author mreyesab
 */
public class FlightServiceEJBDImpl {

    public static Flight findById(int i) {
        Flight flight = new Flight();
        flight.setNumber(new Random().nextInt());
        return flight;
    }

    
}
