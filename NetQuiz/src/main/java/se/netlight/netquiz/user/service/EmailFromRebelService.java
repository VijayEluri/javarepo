/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.user.service;

import java.util.List;
import se.netlight.netquiz.user.model.EmailFromRebel;

/**
 *
 * @author mire
 */
public interface EmailFromRebelService {

    void save(EmailFromRebel emailFromRebel);
    
    void saveAll(List<EmailFromRebel> emails);
    
    void remove(EmailFromRebel emailFromRebel);
    
    void removeAll();
    
    List<EmailFromRebel> findAll();
    
    
    
}
