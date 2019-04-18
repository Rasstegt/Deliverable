package durak;
import java.util.*;

public class Card implements Comparable {

    // Card's fields
    private final String rank;
    private final String suit;
    private final String color; 

    // Creating a random number for future random Generating of bunch of things
    private Random randomNumber = new Random();

    // Constructor that creates a random card
    public Card() {
        int randRankIndex = randomNumber.nextInt(9);
        rank = CardTypes.Values[randRankIndex];
        int randSuitIndex = randomNumber.nextInt(4);
        suit = CardTypes.Suits[randSuitIndex];
        color = CardTypes.Masti.get(suit);

    }

    // Consturctor that creates a card with specified rank and suit
    public Card(String rankArg, String suitArg) {
        if (Arrays.asList(CardTypes.Values).contains(rankArg) && 
            Arrays.asList(CardTypes.Suits).contains(suitArg)) {
            rank = rankArg;
            suit = suitArg;
            color = CardTypes.Masti.get(suit);
        } else {
            throw new IllegalArgumentException("Invalid rank or suit");
        }
    }

    @Override
    public String toString() {
        return "<" + rank + ", " + suit + ">";
    }

    @Override
    // Comparison for/by ranks
    public int compareTo(Object card) {
        Card card2 = (Card) card;
        int value1 = CardTypes.Ranks.get(rank);
        int value2 = CardTypes.Ranks.get(card2.rank);
        return value1 - value2;
    }

    // Comparison for TrumpCard
    // Basically if its a TrumpCard - It may beat any card from different suits
    public int trueCompareTo(Object card, String trumpCard) {
        Card card2 = (Card) card; 
        boolean card1IsTrump = this.isTrump(trumpCard);
        boolean card2IsTrump = card2.isTrump(trumpCard);
        int card1VScard2 = this.compareTo(card);
        if (card1IsTrump && card2IsTrump) {
                return card1VScard2;
        } else if (card1IsTrump && !card2IsTrump) {
                return 1;
        } else if (!card1IsTrump && card2IsTrump) {
                return -1;
        } else if (sameSuit(card)) {
                return card1VScard2;
        } else {
                // If none of these statements matched - throw an exception
                throw new IllegalArgumentException("Different suit");
        }
    }

    public int trueCompareTo(Object card) {
        return trueCompareTo(card, Game.TrumpCard);
    }
 
    public boolean isTrump(String trump) {
        return suit.equals(trump);
    }

    public boolean isTrump() {
        return suit.equals(Game.TrumpCard);
    }

    // In order to beat the card it has to be from the same Suit
    public boolean sameSuit(Object card) {
        Card card2 = (Card) card;
        String card1 = this.suit;
        String suit2 = card2.suit;
        return card1.equals(suit2);
    }

    public String getSuit() {
        return suit;
    }

    public String getColor() {
        return color;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return CardTypes.Ranks.get(rank);
    }
    
}