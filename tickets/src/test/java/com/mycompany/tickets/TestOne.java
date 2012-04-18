/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tickets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author miguel
 */
@RunWith(MockitoJUnitRunner.class)
public class TestOne {
    @Mock
    private Comparable<String> comparable;
    
    @Before
    public void before() {
        System.out.println("### Before");
    }
    
    @Test
    public void foo() {
        Mockito.when(comparable.compareTo("a")).thenReturn(0);
        Mockito.when(comparable.compareTo("b")).thenReturn(1);
        System.out.println("### Test");
        System.out.println(comparable.compareTo("a"));
        System.out.println(comparable.compareTo("b"));
    }
}
