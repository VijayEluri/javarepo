/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author mire
 */
@Entity
@Table(name="authorities")
public class Authority implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String authority;
    
    @ManyToOne
    private User user;
    
    public Authority(){
        
    }
    
    public Authority(String username, String authority, User user){
        this.username = username;
        this.authority = authority;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
