/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.service;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.user.model.EmailFromRebel;
import se.netlight.utils.InMemSessionFactoryGenerator;

/**
 *
 * @author mire
 */
public class EmailFromRebelServiceSprHibImplTest extends TestCase {

    private Session session;
    private SessionFactory sessionFactory;
    private EmailFromRebelService service;

    @Override
    public void setUp() {
        sessionFactory = InMemSessionFactoryGenerator.getInMemSessionFactory();
        service = new EmailFromRebelServiceSprHibImpl(sessionFactory);
    }

    /**
     * Test of save method, of class EmailFromRebelServiceSprHibImpl.
     */
    public void testSaveAndFindAll() {
        EmailFromRebel email = new EmailFromRebel("miguel@reyes.com");

        service.save(email);
        
        assertEquals(1, service.findAll().size());
    }

    /**
     * Test of remove method, of class EmailFromRebelServiceSprHibImpl.
     */
    public void testRemove() {
        EmailFromRebel email = new EmailFromRebel("miguel@reyes.com");
        service.save(email);
        
        assertEquals(1, service.findAll().size());
        service.remove(email);
        
        assertEquals(0, service.findAll().size());

    }
    
    public void testRemoveAll() throws Exception {
        EmailFromRebel email = new EmailFromRebel("miguel@reyes.com");
        service.save(email);
        
        EmailFromRebel email2 = new EmailFromRebel("angel@reyes.com");
        service.save(email2);
        
        service.removeAll();
        
        assertEquals(0, service.findAll().size());
    }
    
    public void testSaveAll() throws Exception {
        
        List<EmailFromRebel> emails = new ArrayList<EmailFromRebel>();
        emails.add(new EmailFromRebel("miguel@test.com"));
        emails.add(new EmailFromRebel("angel@test.com"));
        emails.add(new EmailFromRebel("reyes@test.com"));
        emails.add(new EmailFromRebel("test@test.com"));
        
        service.saveAll(emails);
        
        assertEquals(4, service.findAll().size());

    }
}
