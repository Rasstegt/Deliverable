package durak;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CardTest {
    
    public CardTest() {
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
     * Test of isTrump method, of class Card.
     * Trump is assigned by random, empty string can not be a Trump
     */
    @Test
    public void testIsTrump_String() {
        System.out.println("isTrump");
        String t = "";
        Card instance = new Card();
        boolean expResult = false;
        boolean result = instance.isTrump(t);
        assertEquals(expResult, result);
    }

    /**
     * Test of isTrump method, of class Card.
     * By default Trump is not yet assigned
     */
    @Test
    public void testIsTrump_0args() {
        System.out.println("isTrump");
        Card instance = new Card();
        boolean expResult = false;
        boolean result = instance.isTrump();
        assertEquals(expResult, result);
    }

    
}
