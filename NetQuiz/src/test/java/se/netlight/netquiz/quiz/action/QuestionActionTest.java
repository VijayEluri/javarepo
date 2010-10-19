/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.action;

import com.agical.rmock.extension.junit.RMockTestCase;
import com.opensymphony.xwork2.Action;
import se.netlight.netquiz.quiz.model.Question;
import se.netlight.netquiz.quiz.service.QuestionService;

/**
 *
 * @author mire
 */
public class QuestionActionTest extends RMockTestCase {
    
    private QuestionAction action;
    private QuestionService questionService;
    
    @Override
    public void setUp(){
        questionService = (QuestionService)mock(QuestionService.class);
        action = new QuestionAction();
        action.setQuestionService(questionService);
    }

    public void testPrepareWithIdFound() {
        int id = 5;
        action.setId(id);
        
        questionService.findById(id);
        modify().args(is.AS_RECORDED);
        
        startVerification();
        
        action.prepare();
    }

    public void testExecute() {
        
        questionService.findNew();
        
        startVerification();
        
        assertEquals(Action.SUCCESS, action.execute());
    }

    public void testSave() {
        
        Question question = new Question();
        question.setQuestion("anything");
        question.setPriority(10);
        question.setAnswer("test");
        
        action.setQuestion(question);
        
        questionService.save(question);
        modify().args(is.AS_RECORDED);
        
        questionService.findNew();        
        
        startVerification();
        assertEquals(Action.SUCCESS, action.save());
    }

    public void testRemove() {
        Question question = new Question();
        question.setQuestion("anything");
        question.setPriority(10);
        question.setAnswer("test");
        int id = 1;
        
        action.setQuestion(question);
        action.setId(id);
        
        questionService.remove(id);
        modify().args(is.AS_RECORDED);
        
        questionService.findNew();    
        
        startVerification();
        
        assertEquals(Action.SUCCESS, action.remove());
        
    }
    
    public void testFindOld() throws Exception {
        
        questionService.findOld();
        
        startVerification();
        
        action.findOld();

    }

}
