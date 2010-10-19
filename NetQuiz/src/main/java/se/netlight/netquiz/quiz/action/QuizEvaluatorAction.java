/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.quiz.service.ParticipantService;
import se.netlight.netquiz.quiz.service.QuizService;

/**
 *
 * @author mire
 */
public class QuizEvaluatorAction extends ActionSupport implements Preparable {

    private static Log logger = LogFactory.getLog(QuizEvaluatorAction.class);
    private String email;
    private String uniqueKey;
    private Integer id;
    @Resource
    private ParticipantService participantService;
    @Resource
    private QuizService quizService;
    private Quiz quiz;
    private Map<String, String> questionIdToAnswerMap;
    private int score;
    private boolean start = false;

    @Override
    public String execute() {
        logger.debug("id = " + id);
        //Quiz is initialized in prepare() method
        if (quiz != null) {
            //Fill questionIdToAnswerMap with Id of every question with an empty answer which is replaced when the user inputs it and submits the form
            questionIdToAnswerMap = new HashMap<String, String>();
            Set<Question> questions = quiz.getQuestions();
            for (Question question : questions) {
                questionIdToAnswerMap.put(question.getId().toString(), "");
            }
        }
        return Action.SUCCESS;
    }

    /**
     * 
     * @return ERROR if users email and key don't match, SUCCESS if everything was ok
     */
    public String gradeAnswers() {
        logger.debug("quiz id = " + id);
        logger.debug("email = " + email);
        logger.debug("uniqueKey = " + uniqueKey);
        //First check if user and key correspond
        Participant participant = participantService.findByEmailAndUniqueKey(email, uniqueKey);
        if (participant != null) {
            if (participant.getScore() != null) {
                return "alreadyAnswered";
            } else {
                logger.debug("Email and Key match, user answers are: " + questionIdToAnswerMap);
                //Evaluate answers
                int myScore = evaluateAnswers(quiz.getQuestions(), questionIdToAnswerMap, participant);

                //Add score to participant
                participant.setScore(new Integer(myScore));
                
                //Set participants endedQuizTime
                Timestamp endedQuizTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                participant.setEndedQuiz(endedQuizTime);
                
                //Find how many seconds it took him/her to answer
                float seconds = calculateTimeTakenToAnswer(participant.getStartedQuiz().getTime(), endedQuizTime.getTime());
                logger.debug("seconds = " + seconds);
                participant.setAnsweringTime(seconds);
                
                //Finally persist participant object
                participantService.saveOrUpdate(participant);

                return Action.SUCCESS;
            }

        } else {
           //Wrong email/uniquekey combination
            return Action.ERROR;
        }

    }

    public String startQuiz() {

        logger.debug("quiz id = " + id);
        logger.debug("email = " + email);
        logger.debug("uniqueKey = " + uniqueKey);
        //First check if user and key correspond
        Participant participant = participantService.findByEmailAndUniqueKey(email, uniqueKey);
        if (participant != null) {
            if (participant.getScore() != null) {
                return "alreadyAnswered";
            } else {
                participant.setStartedQuiz(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                participantService.saveOrUpdate(participant);

                this.start = true;
                return Action.SUCCESS;
            }
        } else {
            //Wrong email/uniquekey combination
            return Action.ERROR;
        }
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Map<String, String> getQuestionIdToAnswerMap() {
        return questionIdToAnswerMap;
    }

    public void setQuestionIdToAnswerMap(Map<String, String> questionIdToAnswerMap) {
        this.questionIdToAnswerMap = questionIdToAnswerMap;
    }

    private float calculateTimeTakenToAnswer(long startTime, long endTime) {
        long result = endTime - startTime;        
        return result / 1000;
       
    }

    private int evaluateAnswers(Set<Question> questions, Map<String, String> questionIdToAnswerMap, Participant participant) {
        String userAnswer;
        score = 0;
        for (Question question : questions) {
            userAnswer = questionIdToAnswerMap.get(question.getId().toString());
            //Check if user answers equals real answer
            if (userAnswer.equalsIgnoreCase(question.getAnswer())) {
                score++;
                logger.debug("Correct Answer!");
            }
        //questionIdToRealAnswerMap.put(question.getId().toString(), question.getAnswer());
        }
        logger.debug("Finished evaluating answers, user's score is = " + score);
        return score;

    }

    public void prepare() {
        logger.debug("Find quiz with id = " + id);
        if (id != null) {
            quiz = quizService.findById(id);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
