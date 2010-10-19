/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.action;

import com.agical.rmock.extension.junit.RMockTestCase;
import com.opensymphony.xwork2.Action;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.quiz.service.ParticipantService;
import se.netlight.netquiz.quiz.service.QuizService;

/**
 *
 * @author mire
 */
public class QuizEvaluatorActionTest extends RMockTestCase {

    private QuizEvaluatorAction action;
    private ParticipantService participantService;
    private QuizService quizService;
    private String email = "myEmail";
    private String uniqueKey = "12345";

    @Override
    public void setUp() {
        participantService = (ParticipantService) mock(ParticipantService.class);
        quizService = (QuizService) mock(QuizService.class);
        action = new QuizEvaluatorAction();
        action.setParticipantService(participantService);
        action.setQuizService(quizService);
        action.setEmail(email);
        action.setUniqueKey(uniqueKey);
    }

    public void testExecute() {
        int id = 1;
        action.setId(id);
        Quiz quiz = new Quiz();
        action.setQuiz(quiz);

        assertEquals(Action.SUCCESS, action.execute());
        assertEquals(quiz, action.getQuiz());
    }

    public void testGradeIfValidKeyVsEmail() {
        Quiz quiz = new Quiz();
        action.setQuiz(quiz);
        Participant participant = new Participant(email, uniqueKey);
        participant.setStartedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis() - 10000));

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);

        participantService.saveOrUpdate(participant);
        modify().args(is.AS_RECORDED);

        startVerification();

        assertEquals(Action.SUCCESS, action.gradeAnswers());
        //Answering time is 10 secs
        assertEquals(10.0f, participant.getAnsweringTime());
    }

    public void testCanNotGradeDueToInvalidUniqueKeyVsEmail() throws Exception {

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(null);

        startVerification();

        assertEquals(Action.ERROR, action.gradeAnswers());
    }

    public void testUserHasAllCorrectAnswers() throws Exception {
        Participant participant = new Participant(email, uniqueKey);
        participant.setStartedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis() - 10000));

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);

        participantService.saveOrUpdate(participant);
        modify().args(is.AS_RECORDED);

        startVerification();

        //Set QuizID in Action
        int id = 1;
        action.setId(id);
        Quiz quiz = new Quiz();

        //Create questions, set them to quiz and set quiz to Action
        List<Question> questions = new ArrayList();
        Question question1 = new Question("Question 1", "correct answer", 1);
        question1.setId(new Integer("1"));
        Question question2 = new Question("Question 2", "correct", 1);
        question2.setId(new Integer("2"));
        questions.add(question1);
        questions.add(question2);
        quiz.setQuestions(questions);
        action.setQuiz(quiz);

        //Create user answers
        Map<String, String> questionIdToAnswerMap = new HashMap<String, String>();
        questionIdToAnswerMap.put(question1.getId().toString(), "correct Answer");
        questionIdToAnswerMap.put(question2.getId().toString(), "correct");
        action.setQuestionIdToAnswerMap(questionIdToAnswerMap);

        assertEquals(Action.SUCCESS, action.gradeAnswers());
        assertEquals(2, action.getScore());

    }

    public void testUserHasOneIncorrectAnswers() throws Exception {
        Participant participant = new Participant(email, uniqueKey);
        participant.setStartedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis() - 10000));

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);

        participantService.saveOrUpdate(participant);
        modify().args(is.AS_RECORDED);

        startVerification();

        //Set QuizID in Action
        int id = 1;
        action.setId(id);
        Quiz quiz = new Quiz();

        //Create questions, set them to quiz and set quiz to Action
        List<Question> questions = new ArrayList();
        Question question1 = new Question("Question 1", "correct answer", 1);
        question1.setId(new Integer("1"));
        Question question2 = new Question("Question 2", "correct", 1);
        question2.setId(new Integer("2"));
        questions.add(question1);
        questions.add(question2);
        quiz.setQuestions(questions);
        action.setQuiz(quiz);

        //Create user answers
        Map<String, String> questionIdToAnswerMap = new HashMap<String, String>();
        questionIdToAnswerMap.put(question1.getId().toString(), "some wrong answer");
        questionIdToAnswerMap.put(question2.getId().toString(), "correct");
        action.setQuestionIdToAnswerMap(questionIdToAnswerMap);

        assertEquals(Action.SUCCESS, action.gradeAnswers());
        assertEquals(1, action.getScore());

    }
    
    public void testCanNotGradeCusParticipantAlreadyAnswered() throws Exception {
        Quiz quiz = new Quiz();
        action.setQuiz(quiz);
        Participant participant = new Participant(email, uniqueKey);
        participant.setScore(new Integer(10));

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);

        startVerification();

        assertEquals("alreadyAnswered", action.gradeAnswers());
    }
    
    public void testCanStartQuiz() throws Exception {
        Quiz quiz = new Quiz();
        action.setQuiz(quiz);
        Participant participant = new Participant(email, uniqueKey);

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);
        
        participantService.saveOrUpdate(participant);
        modify().args(is.AS_RECORDED);

        startVerification();

        assertEquals(Action.SUCCESS, action.startQuiz());
        assertNotNull(participant.getStartedQuiz());
        assertTrue(action.isStart());
    }
    
    public void testCanNotStartQuizIfAlreadyAnswered() throws Exception {
        Quiz quiz = new Quiz();
        action.setQuiz(quiz);
        Participant participant = new Participant(email, uniqueKey);
        participant.setScore(new Integer(10));

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(participant);

        startVerification();

        assertEquals("alreadyAnswered", action.startQuiz());
        assertNull(participant.getStartedQuiz());
        assertFalse(action.isStart());
    }
    public void testCanNotStartQuizIfInvalidEmailVsUniqueKey() throws Exception {        

        participantService.findByEmailAndUniqueKey(email, uniqueKey);
        modify().args(is.AS_RECORDED, is.AS_RECORDED);
        modify().returnValue(null);

        startVerification();

        assertEquals(Action.ERROR, action.startQuiz());
        assertFalse(action.isStart());
    }
}
