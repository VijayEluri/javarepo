/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.service;

import com.agical.rmock.extension.junit.RMockTestCase;
import java.util.Date;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.quiz.model.Pair;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.utils.InMemSessionFactoryGenerator;

/**
 *
 * @author miguelreyes
 */
public class QuizServiceSprHibImplTest extends RMockTestCase {

    private QuizService service;
    private Quiz quiz;
    private Session session;
    private Quiz quizTest;
    private SessionFactory sessionFactory;

    @Override
    public void setUp() {
        sessionFactory = InMemSessionFactoryGenerator.getInMemSessionFactory();
        service = new QuizServiceSprHibImpl(sessionFactory);

        quiz = new Quiz();
        quiz.setEndDate(new Date());
        quiz.setName("My quiz");
        quiz.setStartDate(new Date());

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(quiz);
        session.getTransaction().commit();
        session.close();

        quizTest = new Quiz();
        quizTest.setEndDate(new Date());
        quizTest.setName("whiz");
        quizTest.setStartDate(new Date());

    }

    public void testSaveAndFindAll() {

        service.save(quizTest);

        assertEquals(2, service.findAll().size());

    }

    public void testRemove() {

        service.save(quizTest);

        assertEquals(2, service.findAll().size());

        service.delete(quizTest);

        assertEquals(1, service.findAll().size());
    }

    public void testFindByName() {

        service.save(quizTest);

        assertEquals(1, service.findByName("whiz").size());

    }

    public void testFindTwoWithTheSameName() {
        Quiz quizTest2 = new Quiz();
        quizTest2.setName("whiz");

        service.save(quizTest);
        service.save(quizTest2);

        assertEquals(2, service.findByName("whiz").size());
    }

    public void testUpdateName() {
        service.save(quizTest);

        assertEquals("whiz", service.findByName("whiz").get(0).getName());

        quizTest.setName("newname");
        service.save(quizTest);

        assertEquals("newname", service.findByName("newname").get(0).getName());
    }

    public void testSaveQuizWithPairs() throws Exception {
        quizTest.addPair(new Pair());
        quizTest.addPair(new Pair());

        service.save(quizTest);

        assertEquals(2, service.findByName("whiz").get(0).getPairs().size());
    }

    public void testSaveQuizWithPairsAndParticipants() throws Exception {

        Pair pair = new Pair();
        pair.addParticipant(new Participant("participant1@pair1.com", "mykey1"));
        pair.addParticipant(new Participant("participant2@pair1.com", "mykey2"));

        Pair pair2 = new Pair();
        pair2.addParticipant(new Participant("participant1@pair2.com", "mykey1"));
        pair2.addParticipant(new Participant("participant2@pair2.com", "mykey2"));

        quizTest.addPair(pair);
        quizTest.addPair(pair2);

        service.save(quizTest);

        Set<Pair> retrievedPairs = service.findByName("whiz").get(0).getPairs();
        assertEquals(2, retrievedPairs.size());

        //Debugging purposes
        for (Pair pair1 : retrievedPairs) {
            System.out.println("Pair found");
            Set<Participant> participants = pair1.getParticipants();
            assertEquals(2, pair1.getParticipants().size());
            for (Participant participant : participants) {
                System.out.println(". participant:" + participant.getEmail());
            }
        }

    }

    public void testFindById() throws Exception {
        service.save(quizTest);
        assertNotNull(service.findById(quizTest.getId()));

    }

    public void testReturnNullIfIdIsNotFound() throws Exception {
        assertNull(service.findById(69));
    }

    public void testFindOneWinner() throws Exception {
        Quiz myWinningQuiz = new Quiz();

        //Add pairs
        Participant winner = new Participant("participant1@pair1.com", "mykey1");
        winner.setScore(new Integer(3));
        Participant almostMadeIt = new Participant("participant1@pair2.com", "mykey1");
        almostMadeIt.setScore(new Integer(2));

        Pair pair = new Pair();
        pair.addParticipant(winner);
        pair.addParticipant(new Participant("participant2@pair1.com", "mykey2"));

        Pair pair2 = new Pair();
        pair2.addParticipant(almostMadeIt);
        pair2.addParticipant(new Participant("participant2@pair2.com", "mykey2"));

        myWinningQuiz.addQuestion(new Question("what is", "this", 10));
        myWinningQuiz.addQuestion(new Question());
        myWinningQuiz.addQuestion(new Question());
        myWinningQuiz.addPair(pair);
        myWinningQuiz.addPair(pair2);

        //Add questions

        service.save(myWinningQuiz);

        assertEquals(1, service.findWinner(myWinningQuiz).size());

    }

    public void testFindMoreThanOneWinner() throws Exception {
        Quiz myWinningQuiz = new Quiz();

        //Add pairs
        Participant winner = new Participant("participant1@pair1.com", "mykey1");
        winner.setScore(new Integer(3));
        Participant winner2 = new Participant("participant1@pair2.com", "mykey1");
        winner2.setScore(new Integer(3));

        Pair pair = new Pair();
        pair.addParticipant(winner);
        pair.addParticipant(new Participant("participant2@pair1.com", "mykey2"));

        Pair pair2 = new Pair();
        pair2.addParticipant(winner2);
        pair2.addParticipant(new Participant("participant2@pair2.com", "mykey2"));

        myWinningQuiz.addQuestion(new Question("what is", "this", 10));
        myWinningQuiz.addQuestion(new Question());
        myWinningQuiz.addQuestion(new Question());
        myWinningQuiz.addPair(pair);
        myWinningQuiz.addPair(pair2);

        //Add questions

        service.save(myWinningQuiz);

        assertEquals(2, service.findWinner(myWinningQuiz).size());
    }

    public void testQuizHasNoAnswers() throws Exception {
        Quiz myWinningQuiz = new Quiz();

        //Add pairs
        Participant winner = new Participant("participant1@pair1.com", "mykey1");
        winner.setScore(new Integer(3));
        Participant winner2 = new Participant("participant1@pair2.com", "mykey1");
        winner2.setScore(new Integer(3));

        Pair pair = new Pair();
        pair.addParticipant(winner);
        pair.addParticipant(new Participant("participant2@pair1.com", "mykey2"));

        Pair pair2 = new Pair();
        pair2.addParticipant(winner2);
        pair2.addParticipant(new Participant("participant2@pair2.com", "mykey2"));

        myWinningQuiz.addPair(pair);
        myWinningQuiz.addPair(pair2);

        //Add questions

        service.save(myWinningQuiz);

        assertEquals(0, service.findWinner(myWinningQuiz).size());


    }
}
