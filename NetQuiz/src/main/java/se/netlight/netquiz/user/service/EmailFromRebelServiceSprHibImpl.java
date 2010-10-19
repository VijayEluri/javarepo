/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.user.service;

import java.util.List;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.user.model.EmailFromRebel;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author mire
 */
public class EmailFromRebelServiceSprHibImpl extends HibernateDaoSupport implements EmailFromRebelService{
    
    public EmailFromRebelServiceSprHibImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    public void save(EmailFromRebel emailFromRebel) {
        getHibernateTemplate().save(emailFromRebel);
    }

    public void remove(EmailFromRebel emailFromRebel) {
        getHibernateTemplate().delete(emailFromRebel);
    }

    public List<EmailFromRebel> findAll() {
        return getHibernateTemplate().find("from EmailFromRebel");
    }

    public void saveAll(List<EmailFromRebel> emails) {
        getHibernateTemplate().saveOrUpdateAll(emails);
    }

    public void removeAll() {
        getHibernateTemplate().deleteAll(findAll());
    }

}
