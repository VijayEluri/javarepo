/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mire
 */
public class InMemEntityManagerGenerator extends TestCase {

    private static Log logger = LogFactory.getLog(InMemEntityManagerGenerator.class);
    private EntityManager em;
    private EntityManagerFactory emFactory;

    public EntityManager getEntityManager() {
        try {
            logger.info("Starting in-memory HSQL database for unit tests");
            Class.forName("org.hsqldb.jdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
        } catch (Exception ex) {
            fail("Exception during HSQL database startup.");
        }
        try {
            logger.info("Building JPA EntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            fail("Exception during JPA EntityManager instanciation.");
        }
        return em;
    }
}
