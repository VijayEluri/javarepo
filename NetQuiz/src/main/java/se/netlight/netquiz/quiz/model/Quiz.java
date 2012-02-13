/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.model;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import java.util.Date;
import java.util.Set;
import javax.persistence.OneToMany;
import se.netlight.netquiz.quiz.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mire
 */
@Entity
@Table(name="quizes")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Date startDate = new Date();
    private Date endDate = defaultEndDate();
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>();
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pair> pairs = new HashSet<Pair>();

    public Quiz() {

    }

    public Quiz(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @RequiredStringValidator(message = "Supply name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date defaultEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        return cal.getTime();    
    }

    public Set<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions){
        for (Question question : questions) {
            addQuestion(question);
        }        
    }

    public void addPair(Pair pair) {
        pairs.add(pair);
        pair.setQuiz(this);
    }
    
    public void addQuestion(Question q){
        questions.add(q);
        q.setQuiz(this);
        q.setAvailable(false);
    }

    public void removePair(Pair pair) {
        pairs.remove(pair);
        pair.setQuiz(null);
    }

    public Set<Pair> getPairs() {
        return pairs;
    }
}
