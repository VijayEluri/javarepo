/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.service;

import java.util.List;
import se.netlight.netquiz.quiz.model.Question;

/**
 *
 * @author mire
 */
public interface QuestionService {
    
    List<Question> findAll();
    
    List<Question> findNew();
    
    List<Question> findOld();
    
    Question findById(int id);
    
    void save(Question question);
    
    void remove(int id);

}
