package durak;
import java.util.*;

// In Durak there is (9x4) cards:
// 9 Values - Starts from 6, Ends on Ace
// 4 Suits - Hearts, Diamonds, Clubs, Spades
// Class for Constants
public class CardTypes {
    
    public static final String[] Values = {"6", "7", "8", "9", "10", "Jack",
        "Queen", "King", "Ace"};

    public static final Map<String, Integer> Ranks;
    static {
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put("6", 6);
        valuesMap.put("7", 7);
        valuesMap.put("8", 8);
        valuesMap.put("9", 9);
        valuesMap.put("10", 10);
        valuesMap.put("Jack", 11);
        valuesMap.put("Queen", 12);
        valuesMap.put("King", 13);
        valuesMap.put("Ace", 14);
        Ranks = Collections.unmodifiableMap(valuesMap);
    }

    public static final String[] Suits = {"Hearts", "Diamonds", "Clubs",
        "Spades"};

    public static final Map<String, String> Masti;
    static {
        Map<String, String> colorsMap = new HashMap<>();
        colorsMap.put("Hearts", "Red");
        colorsMap.put("Diamonds", "Red");
        colorsMap.put("Clubs", "Black");
        colorsMap.put("Spades", "Black");
        Masti = Collections.unmodifiableMap(colorsMap);
    }
}