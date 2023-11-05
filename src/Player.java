
/**
 * This class represents a Player in the game
 *
 * @author Faycal Kilali
 * @version V1.0
 */
public class Player
{
    private int playerWonRounds = 0;
    private static int numOfPlayers = 0;
    private int playerHealth;
    private int playerNumber;
    private Combo playerCombo;

    /**
     * Constructor for objects of class Player, initializes an empty Combo list and the player's number as ID, as well as the player's health.
     */
    public Player(int inPlayerHealth)
    {
        playerHealth = inPlayerHealth;
        playerNumber = ++numOfPlayers; // Increment then assign the playerNumber
        playerCombo = new Combo();
    }
    
    /**
     * Accessor method for player ID
     * @return player ID
     */
    public int getPlayerID(){
        return playerNumber;
    }
    
    
    /**
     * Accessor Method for player health
     * @return player health
     */
    public int getPlayerHealth(){
        return playerHealth;
    }
    
    /**
     * Mutator method to set player health
     * @param the new health of the player
     */
    public void setPlayerHealth(int inPlayerHealth){
        playerHealth = inPlayerHealth;
    }
    
    /**
     * Accessor Method for player combo
     * @return player health
     */
    public Combo getPlayerCombo(){
        return playerCombo;
    }
    
    /**
     * Accessor Method for number of won rounds
     * @return number of won rounds
     */
    public int getPlayerWonRounds(){
     return playerWonRounds;   
    }
    
    /**
     * Mutator method for number of won rounds
     * @param number of won rounds
     */
    public void setPlayerWonRounds(int inWonRounds){
        playerWonRounds = inWonRounds;
    }
    

    
    

}
