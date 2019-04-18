package durak;
import java.util.*;

public class Deck {

    private Stack<Card> cards;

    // Creates a standard shuffled 36 card deck
    public Deck() {
        cards = new Stack<>();
        ArrayList<Card> allCards = new ArrayList<>();
        for (String rank : CardTypes.Values) {
            for (String suit : CardTypes.Suits) {
                allCards.add(new Card(rank, suit));
                Collections.shuffle(allCards);
            }
        }
        allCards.forEach((card) -> {
            cards.push(card);
        });
    }

    // If empty stack, return null.
    public Card draw() {
        if (!isEmpty())
            return cards.pop();
         else 
            return null;
    }
    
    // Checks if Card object is empty
    public boolean isEmpty() {
        return cards.empty();
    }

    public int size() {
        return cards.size();
    }

    // Reinsert TrumpCard back once its been declared
    public void reinsert(Card trump) {
        cards.add(0, trump);
    }

    @Override
    public String toString() {
        return cards.stream().map((card) -> 
            card + "\n").reduce("[Bottom]\n", String::concat) + "[Top]\n";
    }

}