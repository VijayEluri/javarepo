/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.service;

import java.util.List;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Quiz;

/**
 *
 * @author miguelreyes
 */
public interface QuizService {

    void save(Quiz quiz);
    
    void delete(Quiz quiz);
    
    Quiz findById(int id);
    
    List<Participant> findWinner(Quiz quiz);
    
    List<Quiz> findAll();
    
    List<Quiz> findByName(String name);
    
}
