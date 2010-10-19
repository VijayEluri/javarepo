/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.service;

import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.user.model.Authority;
import se.netlight.netquiz.user.model.User;
import se.netlight.utils.InMemSessionFactoryGenerator;

/**
 *
 * @author miguelreyes
 */
public class UserServiceSprHibImplTest extends TestCase {

    private UserServiceSprHibImpl service;
    private SessionFactory sessionFactory;
    private Session session;
    private String username = "miguel";
    private User user;

    @Override
    protected void setUp() throws Exception {
        sessionFactory = InMemSessionFactoryGenerator.getInMemSessionFactory();
        service = new UserServiceSprHibImpl(sessionFactory);

        user = new User();
        user.setUsername(username);
        user.setEnabled(true);
        user.setPassword("1234");

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void testPersistUser() throws Exception {
        User anotherUser = new User();
        anotherUser.setUsername("tony");
        anotherUser.setEnabled(true);
        anotherUser.setPassword("1234");

        //Add one authority to user
        Authority authority = new Authority();
        authority.setUsername("tony");
        authority.setAuthority("ROLE_ADMIN");
        authority.setUser(anotherUser);

        Set authorities = new TreeSet();
        authorities.add(authority);

        anotherUser.setAuthorities(authorities);

        //Method I want to test in this test
        service.save(anotherUser);

        //check if the authority was saved
        assertEquals(1, service.find(anotherUser.getId()).getAuthorities().size());
    }

    public void testFindAll() throws Exception {
        assertEquals(1, service.findAll().size());
    }

    public void testRemove() throws Exception {

        User u2 = new User();
        u2.setUsername("bono");
        u2.setEnabled(true);
        u2.setPassword("1234");

        service.save(u2);

        assertEquals(2, service.findAll().size());

        //Method I want to test in this test
        service.remove(u2.getId());

        assertEquals(1, service.findAll().size());
    }

    public void testFind() throws Exception {

        assertEquals("miguel", service.find(user.getId()).getUsername());
        assertEquals(true, service.find(user.getId()).isEnabled());
    }
}
