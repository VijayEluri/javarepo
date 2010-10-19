/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.mail;

import junit.framework.TestCase;

/**
 *
 * @author mire
 */
public class EmailServiceImplTest extends TestCase {
    
    private EmailService emailService;
    
    @Override
    public void setUp(){
        emailService = new EmailServiceImpl();
    }
    
    
    public void testSendEmail() {
//        emailService.sendEmail("miguelreyes98@hotmail.com", "info@netlight.se", "testing", "nothing here");
    }

}
