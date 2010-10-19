/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.service;

import java.util.List;
import org.hibernate.SessionFactory;
import se.netlight.netquiz.user.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author miguelreyes
 */
@Repository
public class UserServiceSprHibImpl extends HibernateDaoSupport implements UserService {

    public UserServiceSprHibImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public List<User> findAll() {
        return getHibernateTemplate().find("from User");
    }

    public void save(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public void remove(int id) {
        User user = find(id);
        if (user != null) {
            getHibernateTemplate().delete(user);
        }
    }

    public User find(int id) {
        return (User)getHibernateTemplate().findByNamedParam("from User where id = :id", "id", id).get(0);
    }
}
