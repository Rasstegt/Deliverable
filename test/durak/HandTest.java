package durak;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HandTest {
    
    public HandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Hand.
     * Should add a Card object to Player's Hand
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Card c = null;
        Hand instance = new Hand();
        instance.add(c);
    }

    /**
     * Test of remove method, of class Hand.
     * Should be able to remove a Card object from Player's Hand
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Card c = null;
        Hand instance = new Hand();
        instance.remove(c);
    }

    /**
     * Test of size method, of class Hand.
     * By default Hand doesn't have any card til game has been generated
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Hand instance = new Hand();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of toDraw method, of class Hand.
     * Needs to draw only if Hand's card size is less than 6
     */
    @Test
    public void testNeedsToDraw() {
        System.out.println("needsToDraw");
        Hand instance = new Hand();
        boolean expResult = false;
        boolean result = instance.toDraw();
        assertEquals(expResult, result);
    }

    /**
     * Test of numberToDraw method, of class Hand.
     * Check how many cards needed to be drawn. Hand suppose to have 6
     */
    @Test
    public void testNumberToDraw() {
        System.out.println("numberToDraw");
        Hand instance = new Hand();
        int expResult = 6;
        int result = instance.numberToDraw();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Hand.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Hand instance = new Hand();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
