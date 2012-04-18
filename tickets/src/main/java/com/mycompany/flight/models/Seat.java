/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.models;

/**
 *
 * @author mreyesab
 */
public class Seat {
    
    
    private String number;   
    
    public Seat(String seatNumber) {
        this.number = seatNumber;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
