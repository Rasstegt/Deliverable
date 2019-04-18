package durak;
import java.util.*;

public class Game {
    
    public static String TrumpCard;
    private Player one;
    private Player two;
    private Deck deck;
    private int round; 
    private Player attacker;
    private Player defender;
    private Table currentField;
    private boolean roundInitiated;
    public Scanner in = new Scanner(System.in);
    public Random randomNum = new Random();

    // Constructor where fun begins
    public Game() {
        run();
    }

    // Starts the game and responsible for its continuing after finishing
    private void run() {
        boolean running = true;
        while (running) {
            setup();
            game();
            System.out.println("The game has ended.");
            System.out.println("Play again? y/n");

            boolean validResponse = false;
            while (!validResponse) {
                String response = in.nextLine().toLowerCase();
                switch (response) {
                    case "y":
                        validResponse = true;
                        running = true;
                        break;
                    case "n":
                        validResponse = true;
                        running = false;
                        break;
                    default:
                        validResponse = false;
                        break;
                }
            }
        }
    }

    // Setting up for a game instance
    public void setup() {
        System.out.print("Name of Player 1: ");
        String player1 = in.nextLine();
        System.out.print("Name of Player 2: ");
        String player2 = in.nextLine();
        
        // Shuffling the cards
        deck = new Deck();
        
        // Dealing the cards
        one = new Player(deck, player1);
        two = new Player(deck, player2);
        
        // Determining Trump Card
        Card trumpCard = deck.draw();
        TrumpCard = trumpCard.getSuit();
        System.out.println("The trump is: " + TrumpCard + "!\n");
       
        // Insert Trump Card back
        deck.reinsert(trumpCard);
        
        // Round Counter
        round = 1;
    }

    // Running a game instance
    public void game() {
    // Set a default attacker and defender
    setAttacker(one);
    setDefender(two);    
    
    // Round creation & handling until isVictory()
        boolean gameOver = false;
        while (!gameOver) {
            boolean thisRound = round();

    // Finish the game if win() has been achieved
            if (win()) {
                gameOver = true;
            } else { 
                attacker.replenish();
                defender.replenish();
                round++;
                if (thisRound) {
                    // Attacker wins the round - no roles switching
                } else {
                    switchRoles();
                }
            }
        }
        System.out.println("The winner is " + winner() + "!\n");
    }

    // Victory check
    public boolean win() {
        return (one.isVictory() || two.isVictory());
    }

    // For declaring a winner
    public Player winner() {
        if (win()) {
            if (one.isVictory()) {
                return one;
            } else 
                return two;
        } else {
            throw new IllegalArgumentException();
        }
    }

    // Runs every single round. attacker = true, defender = false
    public boolean round() {
        String roundName = "Round " + round;
        String headerContent = "Attacker: " + attacker + " | " + "Defender: " + defender + "\n";
        roundInitiated = false;
        System.out.println(headerContent);
        System.out.println(roundName + " has began!\n");

        // Round start
        int attack = playerInput(attacker);
        Card attackerCard = attacker.useCard(attack);
        announceCardPlayed(attacker, attackerCard);
        
        // After every attack check for victory - mostly for the last round
        if (win()) {
            return true; 
        }

        // Table generation
        Table roundField = new Table(attackerCard);
        currentField = roundField;
        roundInitiated = true;

        // Checks for the win
        while (!roundField.isCompleted()) {
            boolean defenderTurn = defenderResponse(roundField);
            if (defenderTurn || win()) {
                roundInitiated = false;
                currentField = null;
                return false;
            }
            boolean attackerTurn = attackerResponse(roundField);
            if (attackerTurn || win()) {
                roundInitiated = false;
                currentField = null;
                return true;
            }
        }
        return false;
    }

    public void announceCardPlayed(Player player, Card card) {
        System.out.println(player + " has played " + card);
    }

    // All made for prompts
    public void turnPrompt(Player player) {
        boolean isAttacker = player.isAttacker();

        String preContent = "Cards in a Deck: " + deck.size() + "\n";
        preContent += "Cards in a Hand: " + player.cardsInHand() + "\n";
        String fieldString = "\t\t\t";
        String tail = "\t\t\t";

        String content = "Trump: " + TrumpCard + player.cardList(); 
        content += "=== OTHER OPTIONS ===\n";

        // Specified prompts for attacker and defender
        if (isAttacker) {
            if (roundInitiated) {
                preContent += "\tATTACK\n";
                content += "0 | Beaten\n";
            } else {
                preContent += "\tATTACK\n";
                content += "\n\n";
            }
        tail += player + ", you're attacking!\n";

        } else {
            preContent += "\tDEFENSE\n";
            content += "0 | Take\n";
            tail += player + ", you're defending!\n";
        }

        if (currentField == null) {
            fieldString = "";
        } else {
            fieldString = "" + currentField;
        }
        System.out.println(fieldString + preContent + content + tail);
    }

    // Accepts player input and handles exceptions(depending on how many cards
    // player has in his hands currently
    public int playerInput(Player player) {
        boolean isAttacker = player.isAttacker();
        turnPrompt(player);
        int playerSelection = -1;
        boolean properInput = false;
        while (!properInput) {
            playerSelection = in.nextInt();
            if (isAttacker) {
                if (roundInitiated) {
                    properInput = ((playerSelection >= 0) && (playerSelection <= player.cardsInHand()));
                } else {
                    properInput = ((playerSelection >= 1) && (playerSelection <= player.cardsInHand()));
                }
            } else {
                properInput = ((playerSelection >= 0) && (playerSelection <= player.cardsInHand()));
            }
            if (!properInput) {
                System.out.println("Invalid input. Please enter an acceptable value.");
            }
        }
        return playerSelection;
    }


    // If round has been ended by defender - returns true. 
    // Handles proper input as well
    public boolean defenderResponse(Table table) {
        int defenderResponse = -1;
        boolean properDefenderResponse = false;
        while (!properDefenderResponse) {
            try { 
                defenderResponse = playerInput(defender);
                if (defenderResponse != 0) {
                    Card defenderResponseCard = defender.getCard(defenderResponse);
                    table.respond(defenderResponseCard);
                    properDefenderResponse = true;
                    defender.useCard(defenderResponse);
                    announceCardPlayed(defender, defenderResponseCard);
                    return false;
                } else {
                    properDefenderResponse = true;
                    System.out.println("\n" + defender + 
                    " has chosen to take all cards in the field and end the round!");
                    ArrayList<Card> takenCards = table.fetchCards();
                    takenCards.forEach((card) -> {
                        defender.takeCard(card);
                    });
                    table.finish();
                    return true;
                }
            } catch (IllegalArgumentException e) {
                    System.out.println("\nInvalid defender!");
                    properDefenderResponse = false;
            }
        }
        return true; 
    }

    // Returns true if round was ended by attacker (Beaten/Not Taken)
    // Handles proper input as well
    public boolean attackerResponse(Table table) {
        int attackerResponse = -1;
        boolean properAttackerResponse = false;
        while (!properAttackerResponse) {
            try {
                attackerResponse = playerInput(attacker);
                if (attackerResponse != 0) {
                        Card attackerResponseCard = attacker.getCard(attackerResponse);
                        table.attack(attackerResponseCard);
                        properAttackerResponse = true;
                        attacker.useCard(attackerResponse);
                        announceCardPlayed(attacker, attackerResponseCard);
                        return false;
                } else {
                        System.out.println("\n" + attacker + " has chosen to end the round!");
                        properAttackerResponse = true;
                        table.finish();
                        return true;
                }
            } catch (IllegalArgumentException e) {
                    System.out.println("Invalid attack card!");
                    properAttackerResponse = false;
            }
        }
        return true;
    }

    public boolean whichAttacker() {
        return one.isAttacker();
    }

    public void setAttacker(Player p) {
        attacker = p;
        p.makeAttacker();
    }

    public void setDefender(Player p) {
        defender = p;
        p.makeDefender();
    }

    // Switches the roles if been cards were beaten
    public void switchRoles() {
        Player temp = attacker;
        attacker = defender;
        defender = temp;
        one.switchRole();
        two.switchRole();
    }

    public Player getAttacker() {
        return attacker;
    }

    public Player getDefender() {
        return defender;
    }

    public boolean roundInitiated() {
        return roundInitiated;
    }
}