/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.user.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mire
 */
@Entity
public class EmailFromRebel implements Serializable{
    
    @Id
    private String email;
    
    public EmailFromRebel(){
        
    }
    
    public EmailFromRebel(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
