/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.service;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import se.netlight.netquiz.quiz.model.Participant;

/**
 *
 * @author mire
 */
public class ParticipantServiceSprHibImpl extends HibernateDaoSupport implements ParticipantService{
    
    public ParticipantServiceSprHibImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    public Participant findByEmailAndUniqueKey(String email, String uniqueKey) {
        String[] params = new String[2];
        params[0] = "email";
        params[1] = "uniqueKey";
        
        String[] values = new String[2];
        values[0] = email;
        values[1] = uniqueKey;
        
        List<Participant> results = getHibernateTemplate().findByNamedParam("from Participant where email = :email and uniqueKey = :uniqueKey", params, values);
        if(results.size() == 1){
            return results.get(0);
        } else {
            return null;
        }
    }

    public void saveOrUpdate(Participant p) {
        getHibernateTemplate().saveOrUpdate(p);
    }

}
