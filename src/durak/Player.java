package durak;
import java.util.*;

public class Player {

    private Hand hand;
    private String name;
    private final int id;
    private Deck deck;
    private static int count = 0;
    private boolean attacker;

    // Creates an empty Player and draws from default deck
    public Player() {
        count++;
        id = count;
        name = "Player " + id;
        hand = new Hand();
        deck = new Deck();
        attacker = false;
    }

    // Creates Player with 6 cards in a hand
    public Player(Deck cards) {
        count++; 
        id = count; 
        name = "Player " + id;
        hand = new Hand();
        deck = cards;
        drawCards(6);
        attacker = false;

    }

    // Creates Player with Name and 6 cards in a hand
    public Player(Deck cards, String nickname) {
        count++; 
        id = count; 
        name = nickname;
        hand = new Hand();
        deck = cards;
        drawCards(6);
        attacker = false;
    }

    // If deck is given - draws, not - nothing
    public void draw(Deck deck) {
        if (!deck.isEmpty()) {
            Card thisCard = deck.draw();
            hand.add(thisCard);
        }
    }

    // Associates deck
    public void draw() {
        draw(deck);
    }

    // Draws cards from given deck
    public void drawCards(Deck d, int cards) {
        for (int i = 0; i < cards; i++) 
            draw(deck);
    }

    // Draws cards from associated deck
    public void drawCards(int cards) {
        for (int i = 0; i < cards; i++) 
            draw();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public void discard(Card card) {
        hand.remove(card);
    }

    public int cardsInHand() {
        return hand.size();
    }

    // Draw cards until there is 6 in a Hand
    public void replenish() {
        int toDraw = hand.numberToDraw();
        drawCards(toDraw);
    }

    public boolean isVictory() {
        return ((hand.size() <= 0) && (deck.isEmpty()));
    }

    public boolean isAttacker() {
        return attacker;
    }

    public void makeAttacker() {
        attacker = true;
    }

    public void makeDefender() {
        attacker = false;
    }

    public void switchRole() {
        attacker = !attacker;
    }

    @Override
    public String toString() {
        return name;
    }

    public Card getCard(int num) {
        return hand.getCardByIndex(num - 1);
    }

    public Card useCard(int num) {
        return hand.useCardByIndex(num - 1);
    }

    // ArrayList index+1
    public String cardList() {
        String ret = "\n=== Your Hand ===\n";
        ArrayList<Card> cards = hand.getCards();
        int i = 1;
        for (Card card : cards) {
                ret += i + ") " + card + "\n";
                i += 1;
        }
        return ret;
    }

}