package durak;
import java.util.*;

public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    // Generates random Cards in the Hand
    public Hand(int n) {
        cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Card card1 = new Card();
            cards.add(card1);
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public int size() {
        return cards.size();
    }

    public boolean toDraw() {
        return size() > 6;
    }

    public int numberToDraw() {
        if (toDraw()) {
            return 0; 
        } else 
            return 6 - size();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCardByIndex(int i) {
        return cards.get(i);
    }

    public Card useCardByIndex(int i) {
        return cards.remove(i);
    }

    @Override
    public String toString() {
        return cards.stream().map((c) -> c + "\n").reduce("", String::concat);
    }

}