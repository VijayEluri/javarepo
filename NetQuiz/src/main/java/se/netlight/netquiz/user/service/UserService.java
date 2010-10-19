/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.user.service;

import java.util.List;
import se.netlight.netquiz.user.model.User;

/**
 *
 * @author mire
 */
public interface UserService {    
    
    public List<User> findAll();

    public void save(User user);

    public void remove(int id);

    public User find(int id);

}
