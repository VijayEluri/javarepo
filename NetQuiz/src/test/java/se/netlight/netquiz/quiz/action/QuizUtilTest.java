/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.netlight.netquiz.quiz.action;

import junit.framework.TestCase;

/**
 *
 * @author mire
 */
public class QuizUtilTest extends TestCase {
    
    private QuizUtil util;
    
    public void setUp(){
        util = new QuizUtil();
    }

    public void testMyRandomString() {
        String randomString = util.myRandomString();
        assertNotNull(randomString);
        assertEquals(6, randomString.length());
    }

}
