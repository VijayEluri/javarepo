/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.service;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.utils.InMemSessionFactoryGenerator;

/**
 *
 * @author mire
 */
public class QuestionServiceSprHibImplTest extends TestCase {
    
    private QuestionService service;
    private SessionFactory sessionFactory;
    private Question question;
    private Session session;
    private Quiz quiz;
    
    @Override
    public void setUp(){
        sessionFactory = InMemSessionFactoryGenerator.getInMemSessionFactory();
        
        service = new QuestionServiceSprHibImpl(sessionFactory);
        
        question = new Question();
        question.setQuestion("What's the date today?");
        question.setPriority(10);
        question.setAnswer("some answer");
        question.setAvailable(true);
        
        quiz = new Quiz();
        
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(question);
        session.save(quiz);
        session.getTransaction().commit();
        session.close();
        
    }
    
    public void testFindAll() throws Exception {
        assertEquals(1, service.findAll().size());
    }
    
    public void testRemove() {
        Question questionRemove = new Question();
        questionRemove.setQuestion("your name?");
        questionRemove.setPriority(10);
        questionRemove.setAnswer("miguel");

        service.save(questionRemove);

        assertEquals(2, service.findAll().size());

        service.remove(question.getId());

        assertEquals(1, service.findAll().size());
    }
    
    public void testFindById() throws Exception {
        assertNotNull(service.findById(question.getId()));
    }
    
    public void testReturnNullIfIdIsNotFound() throws Exception {
        assertNull(service.findById(69));
    }
    
    public void testReturnEmptyListIfNoNewQuestionsFound() throws Exception {
        service.remove(question.getId());
        
        assertEquals(0, service.findNew().size());
    }
    
    public void testFindNewQuestions() throws Exception {  
        //Create and save a question with a quiz related to it, this should not be found
        Question questionTest = new Question();
        questionTest.setQuestion("your name?");
        questionTest.setPriority(10);
        questionTest.setAnswer("miguel");
        questionTest.setQuiz(quiz);
        questionTest.setAvailable(false);

        service.save(questionTest);
        
        assertEquals(1, service.findNew().size());
    }
    
    public void testFindOldQuestions() throws Exception {  
        //Create and save a question with a quiz related to it, this should not be found
        Question questionTest = new Question();
        questionTest.setQuestion("your name?");
        questionTest.setPriority(10);
        questionTest.setAnswer("miguel");
        questionTest.setQuiz(quiz);
        questionTest.setAvailable(false);

        service.save(questionTest);
        
        assertEquals(1, service.findOld().size());
    }    

    
}
