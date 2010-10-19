/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.service;

import java.sql.Timestamp;
import java.util.Calendar;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.utils.InMemSessionFactoryGenerator;

/**
 *
 * @author mire
 */
public class ParticipantServiceSprHibImplTest extends TestCase {

    private ParticipantService participantService;
    private String email = "miguel";
    private String uniqueKey = "12345";

    @Override
    public void setUp() {
        SessionFactory sessionFactory = InMemSessionFactoryGenerator.getInMemSessionFactory();
        participantService = new ParticipantServiceSprHibImpl(sessionFactory);

        Participant participant = new Participant(email, uniqueKey);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(participant);
        session.getTransaction().commit();
        session.close();
    }

    public void testFindByEmailAndUniqueKey() {
        assertEquals(email, participantService.findByEmailAndUniqueKey(email, uniqueKey).getEmail());
    }

    public void testNotMatchingReturnsNull() throws Exception {
        assertNull(participantService.findByEmailAndUniqueKey("someEmail", uniqueKey));
    }
    
    public void testSave() throws Exception {
        Participant p = new Participant(email, uniqueKey);
        p.setStartedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        p.setEndedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        participantService.saveOrUpdate(p);
    }
}
