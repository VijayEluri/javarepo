/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.stereotype.Component;
import se.netlight.netquiz.mail.EmailService;
import se.netlight.netquiz.mail.EmailServiceImpl;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Quiz;
import se.netlight.netquiz.quiz.service.QuestionService;
import se.netlight.netquiz.quiz.service.QuizService;
import se.netlight.netquiz.user.model.EmailFromRebel;
import se.netlight.netquiz.user.service.EmailFromRebelService;
import se.netlight.netquiz.utils.HostPrecedingPropertyReader;

/**
 *
 * @author miguelreyes
 */
public class QuizAction extends ActionSupport implements Preparable {

    private static final Log logger = LogFactory.getLog(QuizAction.class);
    //Services used
    @Resource
    private QuizService quizService;
    @Resource
    private EmailFromRebelService emailFromRebelService;
    @Resource
    private QuestionService questionService;
    private List quizes;
    private Quiz quiz;
    private List<EmailFromRebel> emails;
    private List<Participant> winners;
    private Integer id;

    public QuizAction() {
    }

    @SkipValidation
    @Override
    public String execute() {
        //onload show everything
        this.quizes = quizService.findAll();

        return Action.SUCCESS;
    }

    public String remove() {

        quizService.delete(quiz);
        this.quiz = new Quiz();

        return execute();
    }

    public String save() {
        //find users to send emails
        emails = emailFromRebelService.findAll();
        QuizUtil util = new QuizUtil();

        //Create pairs
        util.generatePairsAndParticipants(quiz, emails);

        quiz.setQuestions(questionService.findNew());

        //Save quiz with pairs and questions
        logger.debug("quiz.endDate = " + quiz.getEndDate());
        quizService.save(quiz);

        //Email participants
        informUsers(emails);

        this.quiz = new Quiz();

        return execute();
    }

    @VisitorFieldValidator(message = "")
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List getQuizes() {
        return quizes;
    }

    public void setQuizes(List quizes) {
        this.quizes = quizes;
    }

    public EmailFromRebelService getEmailFromRebelService() {
        return emailFromRebelService;
    }

    public void setEmailFromRebelService(EmailFromRebelService emailFromRebelService) {
        this.emailFromRebelService = emailFromRebelService;
    }

    private void informUsers(List<EmailFromRebel> emails) {
        HostPrecedingPropertyReader propertyReader = new HostPrecedingPropertyReader();
        EmailService emailService = new EmailServiceImpl();
        if (propertyReader.getProperty("sendEmails").equalsIgnoreCase("true")) {
            logger.warn("WARNING: Sending emails to users. IT SHOULD BE ONLY PRODUCTION");
            for (EmailFromRebel emailFromRebel : emails) {
                //TODO uncomment
                //emailService.sendEmail(emailFromRebel.getEmail(), "info@netlight.se", "New quiz created", "body");
            }
        }
    }

    public void prepare(){
        logger.debug("id = " + id);
        if (id != null) {
            quiz = quizService.findById(id);
            this.winners = quizService.findWinner(quiz);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<Participant> getWinners() {
        return winners;
    }

    public void setWinners(List<Participant> winners) {
        this.winners = winners;
    }

    /**
     * @param quizService the quizService to set
     */
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
}
