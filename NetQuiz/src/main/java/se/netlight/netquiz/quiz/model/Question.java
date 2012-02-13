/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author mire
 */
@Entity
@Table(name="questions")
public class Question implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    private String question;
    private String answer;
    private Integer priority;    
    @ManyToOne
    private Quiz quiz;
    @Type(type="yes_no")
    private boolean available = true;
    
    public Question(){
        
    }
    
    public Question(String question, String answer, int priority){
        this.question = question;
        this.answer = answer;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
