/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.action;

import com.agical.rmock.extension.junit.RMockTestCase;
import com.opensymphony.xwork2.Action;
import java.util.ArrayList;
import java.util.List;
import se.netlight.netquiz.mail.EmailService;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.quiz.service.QuestionService;
import se.netlight.netquiz.quiz.service.QuizService;
import se.netlight.netquiz.user.model.EmailFromRebel;
import se.netlight.netquiz.user.service.EmailFromRebelService;

/**
 *
 * @author mire
 */
public class QuizActionTest extends RMockTestCase {

    private QuizAction quizAction;
    private QuizService quizService;
    private EmailService emailService;
    private EmailFromRebelService emailFromRebelService;
    private QuestionService questionService;
    private Quiz quiz;

    @Override
    public void setUp() {
        quizService = (QuizService) mock(QuizService.class);
        questionService = (QuestionService) mock(QuestionService.class);
        emailService = (EmailService) mock(EmailService.class);
        emailFromRebelService = (EmailFromRebelService) mock(EmailFromRebelService.class);

        quizAction = new QuizAction();
        quizAction.setEmailFromRebelService(emailFromRebelService);
        quizAction.setQuestionService(questionService);
        quizAction.setQuiz(quiz);
        quizAction.setQuizService(quizService);
    }

    public void testPrepareWithIdFound() {
        int id = 5;
        quizAction.setId(id);

        quizService.findById(id);
        modify().args(is.AS_RECORDED);

        quizService.findWinner(quiz);
        modify().args(is.AS_RECORDED);

        startVerification();

        quizAction.prepare();
    }

    public void testExecute() {

        quizService.findAll();

        startVerification();

        assertEquals(Action.SUCCESS, quizAction.execute());
    }

    public void testSaveQuizForAThreesome() throws Exception {

        Quiz quizTest = new Quiz();
        quizTest.setName("MyNewQuiz");
        quizAction.setQuiz(quizTest);

        List emails = new ArrayList();
        emails.add(new EmailFromRebel("miguelreyes98@hotmail.com"));
        emails.add(new EmailFromRebel("reyes.miguel@gmail.com"));
        emails.add(new EmailFromRebel("mareyes@kth.se"));

        emailFromRebelService.findAll();
        modify().returnValue(emails);

        List<Question> newQuestions = new ArrayList<Question>();
        newQuestions.add(new Question("question", "answer", 10));
        newQuestions.add(new Question("question2", "answer2", 10));

        questionService.findNew();
        modify().returnValue(newQuestions);

        quizService.save(quizTest);
        modify().args(is.AS_RECORDED);

        quizService.findAll();

        startVerification();

        assertEquals(Action.SUCCESS, quizAction.save());
        assertEquals(1, quizTest.getPairs().size());
        assertEquals(2, quizTest.getQuestions().size());

    }

    public void testSaveQuizForEvenNumberOfUsers() throws Exception {
        Quiz quizTest = new Quiz();
        quizTest.setName("MyNewQuiz");
        quizAction.setQuiz(quizTest);

        List emails = new ArrayList();
        emails.add(new EmailFromRebel("miguelreyes98@hotmail.com"));
        emails.add(new EmailFromRebel("reyes.miguel@gmail.com"));
        emails.add(new EmailFromRebel("mareyes@kth.se"));
        emails.add(new EmailFromRebel("mail1@test.com"));
        emails.add(new EmailFromRebel("mail2@gmail.com"));
        emails.add(new EmailFromRebel("mail3@kth.se"));

        emailFromRebelService.findAll();
        modify().returnValue(emails);

        List<Question> newQuestions = new ArrayList<Question>();
        newQuestions.add(new Question("question", "answer", 10));
        newQuestions.add(new Question("question2", "answer2", 10));

        questionService.findNew();
        modify().returnValue(newQuestions);

        quizService.save(quizTest);
        modify().args(is.AS_RECORDED);

        quizService.findAll();

        startVerification();

        assertEquals(Action.SUCCESS, quizAction.save());
        assertEquals(3, quizTest.getPairs().size());
        assertEquals(2, quizTest.getQuestions().size());

    }

    public void testSaveQuizForOddNumberOfUsers() throws Exception {
        Quiz quizTest = new Quiz();
        quizTest.setName("MyNewQuiz");
        quizAction.setQuiz(quizTest);

        List emails = new ArrayList();
        emails.add(new EmailFromRebel("miguelreyes98@hotmail.com"));
        emails.add(new EmailFromRebel("reyes.miguel@gmail.com"));
        emails.add(new EmailFromRebel("mareyes@kth.se"));
        emails.add(new EmailFromRebel("mail1@test.com"));
        emails.add(new EmailFromRebel("mail2@gmail.com"));
        emails.add(new EmailFromRebel("mail3@kth.se"));
        emails.add(new EmailFromRebel("mail4@test.com"));
        emails.add(new EmailFromRebel("mail5@gmail.com"));
        emails.add(new EmailFromRebel("mail6@kth.se"));
        emails.add(new EmailFromRebel("mail7@kth.se"));
        emails.add(new EmailFromRebel("mail8@kth.se"));

        emailFromRebelService.findAll();
        modify().returnValue(emails);

        List<Question> newQuestions = new ArrayList<Question>();
        newQuestions.add(new Question("question", "answer", 10));
        newQuestions.add(new Question("question2", "answer2", 10));

        questionService.findNew();
        modify().returnValue(newQuestions);

        quizService.save(quizTest);
        modify().args(is.AS_RECORDED);

        quizService.findAll();

        startVerification();

        assertEquals(Action.SUCCESS, quizAction.save());
        assertEquals(5, quizTest.getPairs().size());
        assertEquals(2, quizTest.getQuestions().size());
    }
}
