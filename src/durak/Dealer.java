package durak;
import java.util.*;

public class Dealer {

    private Card attacker;
    private Card defender;
    private boolean completed;

    // Check if it received a response
    public Dealer(Card attackCard) {
        attacker = attackCard; 
        completed = false; 
    }

    // Reponse of the defender
    public void response(Card defenseCard) {
        setDefender(defenseCard);
        toggleCompleted();
    }

    public Card getAttacker() {
        return attacker;
    }

    public Card getDefender() {
        return defender;
    }

    public boolean isCompleted() {
        return completed;
    }

    // If defender's card is valid(same suit, higer value) - returns true
    public boolean isValidDefender(Card defenseCard) {
        try {
            if (defenseCard.trueCompareTo(attacker) > 0) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }
    
    // Sets the defender's card as valid if attacker's card is beaten
    public void setDefender(Card defenseCard) {
        if (isValidDefender(defenseCard)) {
            defender = defenseCard;
        } else 
            throw new IllegalArgumentException("You can't beat attacker's"
                + " card using this card");
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    // Returns ArrayList with 1 or 2 cards if Dealer is completed/not
    public ArrayList<Card> fetchCards() {
        ArrayList<Card> back = new ArrayList<>();
        if (completed) {
            back.add(attacker);
            back.add(defender);
        } else 
            back.add(attacker);
        return back;
    }

    @Override
    public String toString() {
            return fetchCards().stream().map((card) -> 
                "\t\t\t" + card + "\n").reduce("\t\t\t{Attacker}\n\t\t\t",
                    String::concat) + "\t\t\t{Defender}\n";
    }
}