package durak;
import java.util.*;

public class Table {

    private ArrayList<Dealer> pairs;
    private ArrayList<String> playedRanks;
    private boolean completed;

    // Empty Table
    public Table() {
        pairs = new ArrayList<>();
        playedRanks = new ArrayList<>();
        completed = false;
    }

    // Creates object when attack starts
    public Table(Card attackCard) {
        pairs = new ArrayList<>();
        playedRanks = new ArrayList<>();
        completed = false;

        Dealer pair = new Dealer(attackCard);
        pairs.add(pair);

        String cardRank = attackCard.getRank();
        playedRanks.add(cardRank);

    }

    // Adds a new pair of attacking cards
    public void attack(Card attackCard) {
        if (canAttack() && isValidAttack(attackCard)) {
            Dealer newAttackPair = new Dealer(attackCard);
        } else {
            throw new IllegalArgumentException("You can't attack.");
        }
    }

    // Responds to the new pair of attacking card
    public void respond(Card responseCard) {
        if (openPair()) {
            Dealer open = currentOpenPair();
            open.response(responseCard);
        }
    }

    // Checks if attack is valid by checking the rank on the table
    public boolean isValidAttack(Card attackCard) {
        String thisRank = attackCard.getRank();
        return playedRanks.stream().anyMatch((rank) -> 
            (thisRank.equals(rank)));
    }

    public boolean canAttack() {
        return !openPair();
    }

    public boolean openPair() {
        return pairs.stream().anyMatch((pair) -> (!pair.isCompleted()));
    }

    public boolean isCompleted() {
        return completed;
    }

    // Returns if there is any open pairs
    public Dealer currentOpenPair() {
        Dealer back = null;
        for (Dealer pair : pairs) 
            if (!pair.isCompleted()) 
                back = pair;
            
        if (back != null) 
            return back;
         else 
            throw new IllegalArgumentException("There are no open pairs.");
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    // finishes the round - true: succesfull attack - false: successful defense
    public boolean finish() {
        toggleCompleted();
        return openPair();
    }

    // Used when round is finished
    public ArrayList<Card> fetchCards() {
        ArrayList<Card> back = new ArrayList<>();
        pairs.stream().map((pair) -> 
            pair.fetchCards()).forEachOrdered((pairCards) -> {
            pairCards.forEach((card) -> {
                back.add(card);
            });
        });
        return back;
    }

    @Override
    public String toString() {
        return pairs.stream().map((pair) ->
            "\t\t\t" + pair).reduce("\t\t\t||| Field |||\n",
                String::concat) + "\t\t\t||| Field |||\n";
    }

}