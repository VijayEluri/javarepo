package org.miguel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
/**
 * Unit test for simple MyCacheManager.
 */
public class MyCacheManagerTest {

    private MyCacheManager instance;

    @Before
    public void setUp() {
        instance= new MyCacheManager();
        instance.createCache();
    }

    @After
    public void tearDown() {
        instance.destroyCache();
    }

    @Test
    public void testAddToCache() {

        instance.addNode();

        //Verify that the Fqn object is there and that the RootNode has a child with that Fqn.
        assertEquals(instance.getFredrikNilssonFqn(), instance.getFredrikNilsson().getFqn());
        assertTrue(instance.getRootNode().hasChild(instance.getFredrikNilssonFqn()));

        //Verify the type of the entries.
        assertTrue((Boolean) instance.getFredrikNilsson().get("isJavaArchitect"));
        assertEquals(Coffee.class, instance.getFredrikNilsson().get("favoriteDrink").getClass());

        //Verify that the keys are there.
        Set keys = new HashSet();
        keys.add("isJavaArchitect");
        keys.add("favoriteDrink");
        assertEquals(keys, instance.getFredrikNilsson().getKeys());

        //Remove key from Node.
        instance.removeKeyFromNode(instance.getFredrikNilsson(), "favoriteDrink");
        assertNull(instance.getFredrikNilsson().get("favoriteDrink"));

        //Remove Fqn child from Root node.
        instance.removeChild(instance.getFredrikNilssonFqn());
        assertFalse(instance.getRootNode().hasChild(instance.getFredrikNilssonFqn()));
    }
}
