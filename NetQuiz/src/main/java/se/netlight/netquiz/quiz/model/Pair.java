/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author miguelreyes
 */
@Entity
public class Pair implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    
    @ManyToOne
    private Quiz quiz;
    
    @OneToMany(mappedBy = "pair", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Participant> participants = new HashSet<Participant>();
    
    public Pair(){      
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void addParticipant(Participant participant){
        participants.add(participant);
        participant.setPair(this);
    }
    
    public void removeParticipant(Participant participant){
        participants.remove(participant);
        participant.setPair(null);
    }

    public Set<Participant> getParticipants() {
        return participants;
    }
    

}
