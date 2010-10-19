/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import se.netlight.netquiz.quiz.model.Pair;
import se.netlight.netquiz.quiz.model.Participant;
import se.netlight.netquiz.quiz.model.Quiz;

/**
 *
 * @author miguelreyes
 */
public class QuizServiceSprHibImpl extends HibernateDaoSupport implements QuizService {

    public QuizServiceSprHibImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public void save(Quiz quiz) {
        getHibernateTemplate().save(quiz);
    }

    public void delete(Quiz quiz) {
        getHibernateTemplate().delete(quiz);
    }

    public List<Quiz> findAll() {
        return getHibernateTemplate().find("from Quiz");
    }

    public List<Quiz> findByName(String name) {
        return getHibernateTemplate().findByNamedParam("from Quiz where name = :name", "name", name);
    }

    public Quiz findById(int id) {
        List result = getHibernateTemplate().findByNamedParam("from Quiz where id = :id", "id", id);
        if (result.size() == 1) {
            return (Quiz) result.get(0);
        } else {
            return null;
        }
    }

    public List<Participant> findWinner(Quiz quiz) {
        List<Participant> winners = new ArrayList<Participant>();
        int maxScore = quiz.getQuestions().size();
        System.out.println("maxScore = " + maxScore);
        Set<Pair> pairs = quiz.getPairs();
        Set<Participant> participants;
        while (maxScore > 0 && winners.size() == 0) {
            for (Pair pair : pairs) {
                participants = pair.getParticipants();
                Integer score;
                for (Participant participant : participants) {
                    score = participant.getScore();
                    if (score != null && score.intValue() == maxScore) {
                        System.out.println("Winner found");
                        winners.add(participant);
                    }
                }
            }
            maxScore--;
        }
        return winners;
    }
}
