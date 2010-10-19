/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.quiz.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import se.netlight.netquiz.quiz.model.Question;

/**
 *
 * @author mire
 */
public class QuestionServiceSprHibImpl extends HibernateDaoSupport implements QuestionService {

    public QuestionServiceSprHibImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<Question> findAll() {
        return getHibernateTemplate().find("from Question");
    }

    public Question findById(int id) {
        List result = getHibernateTemplate().findByNamedParam("from Question where id = :id", "id", id);
        if (result.size() == 1) {
            return (Question) result.get(0);
        } else {
            return null;
        }
    }

    public void save(Question question) {
        getHibernateTemplate().saveOrUpdate(question);
    }

    public void remove(int id) {
        Question question = findById(id);
        if (question != null) {
            getHibernateTemplate().delete(question);
        }
    }

    public List<Question> findNew() {
        return getHibernateTemplate().find("from Question where available = 'Y'");
    }

    public List<Question> findOld() {
        return getHibernateTemplate().find("from Question where available = 'N'");
    }
}
