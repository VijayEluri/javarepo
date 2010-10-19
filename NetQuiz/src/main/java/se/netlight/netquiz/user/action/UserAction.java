/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import se.netlight.netquiz.user.model.Authority;
import se.netlight.netquiz.user.model.User;
import se.netlight.netquiz.user.service.UserService;

/**
 *
 * @author mire
 */
public class UserAction implements Preparable {

    private static Log logger = LogFactory.getLog(UserAction.class);
    @Resource
    private UserService userService;
    private List<User> users;
    private User user;
    private Integer id;

    public UserAction() {
    }

    public String execute() {
        
        //When page is loaded read information about all users
        this.users = userService.findAll();
        
        //Debugging only
        for (User itUser : users) {
            logger.debug("itUser = " + itUser.getUsername());
            logger.debug(" . . user.getAuthorities = " + itUser.getAuthorities().toString());
        }

        return Action.SUCCESS;
    }

    /**
     * This method handles either save or update
     * 
     * @return Success that comes from the execute() method
     */
    public String save() {
        if (user.getId() != null) {
            //This is necessary to get previous Authorities related to the user
            User updatedUser = userService.find(user.getId());
            
            //Replace values with form values            
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            
            //Enable the user so he/she can log in
            updatedUser.setEnabled(true);
            //Make user ADMIN
            giveAdminAuthority(updatedUser);
            
            userService.save(updatedUser);
        } else{
            //Enable the user so he/she can log in
            user.setEnabled(true);
            //Make user ADMIN
            giveAdminAuthority(user);
            
            userService.save(user);
        }
        this.user = new User();
        return execute();
    }

    public String remove() {
        userService.remove(id);
        this.user = new User();
        return execute();
    }

    public List<User> getUsers() {
        return users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null) {
            user = userService.find(id);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This method works for 3 scenarios: 
     * <p> 1. User has no roles at all
     * <p> 2. User has a role different than ROLE_ADMIN
     * <p> 3. User already has the ROLE_ADMIN role
     * 
     * In every case, the purpose is to assign the ROLE_ADMIN to the user if it's not present already
     * 
     * @param user User that will get the ROLE_ADMIN role in the Authorizations table
     */
    private void giveAdminAuthority(User user) {
        boolean found = false;
        Set<Authority> foundAuthorities = user.getAuthorities();
        String username = user.getUsername();
        logger.debug("username = " + username);

        if (foundAuthorities != null && foundAuthorities.size() > 0) {
            logger.debug("foundAuthorities.size = " + foundAuthorities.size());
            foundAuthorities.iterator();
            for (Authority authority : foundAuthorities) {
                logger.debug("authority = " + authority.getAuthority());
                if (authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
                    //Already Admin
                    found = true;
                    logger.debug(" User " + username + " already has role ROLE_ADMIN");
                }
            }
        } 
        if (foundAuthorities == null || !found) {
            //User had no roles at all or another role that is not admin
            Set<Authority> authorities = new TreeSet();
            authorities.add(new Authority(user.getUsername(), "ROLE_ADMIN", user));
            user.setAuthorities(authorities);
            logger.debug("ROLE_ADMIN not found. Added ROLE_ADMIN to " + username);

        }
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
