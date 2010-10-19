/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author miguelreyes
 */
@Entity
public class Participant implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;    
    private String email;
    private String uniqueKey;
    private Integer score;
    private float answeringTime;
    
    private Timestamp startedQuiz;
    private Timestamp endedQuiz;
 
    @ManyToOne
    private Pair pair;
    
    public Participant(){        
    }
    
    public Participant(String email, String uniqueKey){
        this.email = email;
        this.uniqueKey = uniqueKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getAnsweringTime() {
        return answeringTime;
    }

    public void setAnsweringTime(float time) {
        this.answeringTime = time;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pairs) {
        this.pair = pairs;
    }

    public Timestamp getStartedQuiz() {
        return startedQuiz;
    }

    public void setStartedQuiz(Timestamp startedQuiz) {
        this.startedQuiz = startedQuiz;
    }

    public Timestamp getEndedQuiz() {
        return endedQuiz;
    }

    public void setEndedQuiz(Timestamp endedQuiz) {
        this.endedQuiz = endedQuiz;
    }
    
}
