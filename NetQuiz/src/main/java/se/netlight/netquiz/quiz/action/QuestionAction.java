/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.service.QuestionService;

/**
 * This class can't extend from ActionSupport otherwise when Ajax is used in the JSP the action is not called
 * @author mire
 */
public class QuestionAction implements Preparable{
    
    private static Log logger = LogFactory.getLog(QuestionAction.class);
    
    private Question question;
    private Integer id;
    @Resource
    private QuestionService questionService;
    private List<Question> questions;
    
    public QuestionAction(){
    }

    public void prepare() {
        logger.debug("id = " + id);
        if(id != null){
            question = questionService.findById(id);
        }
    }
    
    public String findOld(){
        questions = questionService.findOld();
        
        return Action.SUCCESS;
    }

    public String execute(){
        logger.debug("Execute called");
        questions = questionService.findNew();
        
        return Action.SUCCESS;
    }
    
    public String save(){
        logger.debug("Save called");
        logger.debug("question.getId() = " + question.getId());
        if (question.getId() != null) {
            Question updatedQuestion = questionService.findById(question.getId());
            
            updatedQuestion.setQuestion(question.getQuestion());
            updatedQuestion.setAnswer(question.getAnswer());
            updatedQuestion.setPriority(question.getPriority());
            
            questionService.save(updatedQuestion);
        } else {
            questionService.save(question);
        }
        this.question = new Question();
        
        return execute();
               
    }
    
    public String remove(){
        logger.debug("question = " + question);
        questionService.remove(id);
        
        this.question = new Question();
        
        return execute();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * @param questionService the questionService to set
     */
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

}
