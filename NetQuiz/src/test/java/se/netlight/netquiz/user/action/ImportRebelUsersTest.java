/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.user.action;


import com.agical.rmock.extension.junit.RMockTestCase;
import com.opensymphony.xwork2.Action;
import java.util.ArrayList;
import se.netlight.netquiz.user.model.EmailFromRebel;
import se.netlight.netquiz.user.service.EmailFromRebelService;
/**
 *
 * @author djhe
 */
public class ImportRebelUsersTest extends RMockTestCase {
    
    private ImportRebelUsers instance;  
    private EmailFromRebelService emailFromRebelService;

    @Override
    protected void setUp() throws Exception {
        emailFromRebelService = (EmailFromRebelService)mock(EmailFromRebelService.class);
        instance =  new ImportRebelUsers();
        instance.setEmailFromRebelService(emailFromRebelService);
    }

    
    public void testImportUsers() {
        /*
        emailFromRebelService.removeAll();
        
        emailFromRebelService.saveAll(new ArrayList<EmailFromRebel>());
        modify().args(is.ANYTHING);
        
        startVerification();
        assertEquals(Action.SUCCESS, instance.importUsers());
        
        * */
        
    }

}
