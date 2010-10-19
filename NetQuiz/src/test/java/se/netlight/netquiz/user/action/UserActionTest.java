/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.netquiz.user.action;

import se.netlight.netquiz.user.action.UserAction;
import com.agical.rmock.extension.junit.RMockTestCase;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import se.netlight.netquiz.user.model.Authority;
import se.netlight.netquiz.user.model.User;
import se.netlight.netquiz.user.service.UserService;

/**
 *
 * @author mire
 */
public class UserActionTest extends RMockTestCase {

    private static Log logger = LogFactory.getLog(UserActionTest.class);
    private UserAction action;
    private UserService service;
    private User user;
    private String username;
    private int userId = 1;

    @Override
    public void setUp() {
        service = (UserService) mock(UserService.class);
        user = new User();
        username = "miguel";
        action = new UserAction();
        action.setUserService(service);
        action.setUser(user);

    }
    
    public void testSaveShoulInitializeWithAdminRoleIfUserHasNoRoles() throws Exception {
        user.setUsername(username);
        user.setPassword("pass");
        user.setId(null);

        service.save(user);
        modify().args(is.ANYTHING);

        service.findAll();
        modify().returnValue(new ArrayList());
                      
        startVerification();
        
        assertEquals("success", action.save());
    }

    public void testUpdateShoulInitializeWithAdminRoleIfUserHasNoRoles() throws Exception {
        user.setUsername(username);
        user.setPassword("pass");
        user.setId(userId);

        service.save(user);
        modify().args(is.AS_RECORDED);

        service.findAll();
        modify().returnValue(new ArrayList());
        
        service.find(userId);
        modify().returnValue(user);
        modify().args(is.AS_RECORDED);
               
        startVerification();

        assertEquals("success", action.save());
    }

    public void testAddAdminRoleToAlreadyExistingRoles() throws Exception {
        user.setUsername(username);
        user.setPassword("pass");
        user.setId(userId);

        service.save(user);
        modify().args(is.AS_RECORDED);

        service.findAll();
        modify().returnValue(new ArrayList());
        
        service.find(userId);   
        modify().returnValue(user);
        modify().args(is.AS_RECORDED);

        startVerification();

        //I want this user to already have a role, so it just adds a new role
        Authority authority = new Authority();
        authority.setUsername(username);
        authority.setAuthority("ROLE_CONSULTANT");
        authority.setUser(user);
        Set authorities = new TreeSet();
        authorities.add(authority);
        user.setAuthorities(authorities);


        assertEquals("success", action.save());
    //assertEquals(true, action.getUser().isEnabled());
    //assertEquals(1, action.getUser().getAuthorities().size());

    }

    public void testDontSaveAdminRoleIfTheUserAlreadyHasIt() throws Exception {

        user.setUsername(username);
        user.setPassword("pass");
        user.setId(userId);

        //Add one authority to user
        Authority authority = new Authority();
        authority.setUsername(username);
        authority.setAuthority("ROLE_ADMIN");
        authority.setUser(user);
        Set authorities = new TreeSet();
        authorities.add(authority);
        user.setAuthorities(authorities);

        service.save(user);
        modify().args(is.AS_RECORDED);

        service.findAll();
        modify().returnValue(new ArrayList());
        
        service.find(userId);   
        modify().returnValue(user);
        modify().args(is.AS_RECORDED);

        startVerification();

        assertEquals("success", action.save());
        //assertEquals(1, action.getUser().getAuthorities().size());
    }
}
