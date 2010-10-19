/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.netlight.utils;

import se.netlight.netquiz.user.service.*;
import org.springframework.test.AbstractTransactionalSpringContextTests;

/**
 *
 * This class actually reads the spring context configuration file so if this test passes then the Web Server will initialize
 * Note: It actually opens the connection to the DB, so if the DB is down the test will break
 * 
 * @author mire
 */
public class ContextConfigTest extends AbstractTransactionalSpringContextTests {

    private UserService userService;

    @Override
    protected String[] getConfigLocations() {
        setDependencyCheck(false);
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[]{"applicationContext-netquiz.xml"};
    }

    public void testPersistenceUnits() {
        assertNotNull(this.userService.findAll());
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
