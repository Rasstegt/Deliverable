package durak;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest {
    
    public TableTest() {
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
     * Test of canAttack method, of class Table.
     * Player 1 by default is Attacker
     */
    @Test
    public void testCanAttack() {
        System.out.println("canAttack");
        Table instance = new Table();
        boolean expResult = true;
        boolean result = instance.canAttack();
        assertEquals(expResult, result);
    }

    /**
     * Test of openPair method, of class Table.
     * By default there is no "OpenPairs" on the table
     */
    @Test
    public void testAnyOpenPairs() {
        System.out.println("anyOpenPairs");
        Table instance = new Table();
        boolean expResult = false;
        boolean result = instance.openPair();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCompleted method, of class Table.
     * If attack/defense is completed - may proceed to the new round
     */
    @Test
    public void testIsCompleted() {
        System.out.println("isCompleted");
        Table instance = new Table();
        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
    }
    
}
