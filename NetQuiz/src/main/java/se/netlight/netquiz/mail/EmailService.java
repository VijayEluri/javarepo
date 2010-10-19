/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.mail;

/**
 *
 * @author mire
 */
public interface EmailService {

    void sendEmail(String to, String from, String subject, String body);
    
    
}
