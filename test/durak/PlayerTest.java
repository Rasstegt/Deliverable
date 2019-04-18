package durak;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of draw method, of class Player.
     * Player must be able to draw the cards
     */
    @Test
    public void testDraw_0args() {
        System.out.println("draw");
        Player instance = new Player();
        instance.draw();
    }

    /**
     * Test of drawCards method, of class Player.
     * Draw Card with two arguments must be able to add cards to the player from 
     * existing deck
     */
    @Test
    public void testDrawCards_Deck_int() {
        System.out.println("drawCards");
        Deck d = null;
        int n = 0;
        Player instance = new Player();
        instance.drawCards(d, n);
    }

    /**
     * Test of drawCards method, of class Player.
     * Same idea with method above
     */
    @Test
    public void testDrawCards_int() {
        System.out.println("drawCards");
        int n = 0;
        Player instance = new Player();
        instance.drawCards(n);
    }

    /**
     * Test of takeCard method, of class Player.
     * Add card to the Player's Hand if he lost the round
     */
    @Test
    public void testTakeCard() {
        System.out.println("takeCard");
        Card c = null;
        Player instance = new Player();
        instance.takeCard(c);
    }

    /**
     * Test of discard method, of class Player.
     * Remove card from the Player's Hand if round is over
     */
    @Test
    public void testDiscard() {
        System.out.println("discard");
        Card c = null;
        Player instance = new Player();
        instance.discard(c);
    }

    /**
     * Test of cardsInHand method, of class Player.
     * How many cards player has in his hand, from default its 0
     */
    @Test
    public void testCardsInHand() {
        System.out.println("cardsInHand");
        Player instance = new Player();
        int expResult = 0;
        int result = instance.cardsInHand();
        assertEquals(expResult, result);
    }

    /**
     * Test of replenish method, of class Player.
     * Player must be able to refresh his deck by the end of the round
     */
    @Test
    public void testReplenish() {
        System.out.println("replenish");
        Player instance = new Player();
        instance.replenish();
    }

    /**
     * Test of isVictory method, of class Player.
     * If one of the players win - win will be declared/ by default its false
     */
    @Test
    public void testVictoryAchieved() {
        System.out.println("victoryAchieved");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.isVictory();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAttacker method, of class Player.
     * Check if Player is attacker
     */
    @Test
    public void testIsAttacker() {
        System.out.println("isAttacker");
        Player instance = new Player();
        boolean expResult = false;
        boolean result = instance.isAttacker();
        assertEquals(expResult, result);
    }

    /**
     * Test of switchRole method, of class Player.
     * Player has a behavior of switching roles between Attacker/Defender
     */
    @Test
    public void testSwitchRole() {
        System.out.println("switchRole");
        Player instance = new Player();
        instance.switchRole();
    }
    
    /**
     * Test of makeAttacker method, of class Player.
     * Assigns Player object to become an attacker
     */
    @Test
    public void testMakeAttacker() {
        System.out.println("isAttacker");
        Player instance = new Player();
        instance.makeAttacker();
        boolean expResult = true;
        boolean result = instance.isAttacker();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of makeDefender method, of class Player.
     * Assigns Player object to become a defender 
     */
    @Test
    public void testMakeDefender() {
        System.out.println("isDefender");
        Player instance = new Player();
        instance.makeDefender();
        boolean expResult = false;
        boolean result = instance.isAttacker();
        assertEquals(expResult, result);
    }


    
}
