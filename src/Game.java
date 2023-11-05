import java.util.Scanner;

/**
 * Class that executes and ensures the game's rules are being abidden.
 *
 * @author Faycal Kilali
 * @version V1.0
 */ 
public class Game
{
    private static final int PLAYER_ONE_HEALTH = 100;
    private static final int PLAYER_TWO_HEALTH = 100;
    private static Player playerOne;
    private static Player playerTwo;
    
    
    /**
     * The beginGame method simulates a fighting game. The players take turns to perform actions. The actions can be adding a move to a Combo at any position or perform their combo. If the player performs the combo, they deal the prorated damage to the other player, then they remove all the moves from their combo as part of the game rules. If the total damage dealt to the player reaches the relevant CONSTANT of the player in value, the player is declared defeated, an increment occurs to the winning player's constant, and a new round begins.
     */
    public static void beginGame()
    {
        
        // Setting up the players
        playerOne = new Player(PLAYER_ONE_HEALTH);
        playerTwo = new Player(PLAYER_TWO_HEALTH);
        rounds();

    }
    
    /**
     * Helper-method for rounds for recursion purposes
     */
    private static void rounds(){
        playerOne.setPlayerHealth(PLAYER_ONE_HEALTH);
        playerTwo.setPlayerHealth(PLAYER_TWO_HEALTH);
        // Printing the current statistics of the players
        String newRoundStart = "\n" +
                      "************************************\n" +
                      "        New Round Start              \n" +
                      "************************************\n";
        String gameRounds = ("Player " + playerOne.getPlayerID() +   " won rounds: " + playerOne.getPlayerWonRounds() + "\n" + "Player "+ playerTwo.getPlayerID() +  " won rounds: " + playerTwo.getPlayerWonRounds() + "\n");
        System.out.println(newRoundStart);
        System.out.println(gameRounds);

        // Printing the health and current combo of each player
        System.out.println(gameInfo());
        
        while (checkGameOver(playerOne, playerTwo) == false){
            
            playerTurn(playerOne, playerTwo);
            if (checkGameOver(playerOne, playerTwo) == false){
                playerTurn(playerTwo, playerOne);
            }
            
            
            
            // Showing the new health and combo of the players
            System.out.println(gameInfo());
        }
        rounds();
    }
    
    /**
     * Helper-method to check if the game is over
     * @param the two players of the game
     * @return true if the inclusive disjunction is true, otherwise false.
     */
    private static boolean checkGameOver(Player playerOne, Player playerTwo){
        return ((playerOne.getPlayerHealth() <= 0) || (playerTwo.getPlayerHealth() <= 0)); // Returns true if the disjunction, inclusive, is true.
    }
    
    
    /**
     * Helper method for game information to present information more concisely.
     * @return the current health and the current combo of all the players
     */
    private static String gameInfo(){
        String gameInformation = "";
        gameInformation = ("Player " + playerOne.getPlayerID() +  " Health: " + playerOne.getPlayerHealth() + "\n" + playerOne.getPlayerCombo().toString() + "\n" + "Player " + playerTwo.getPlayerID() + " Health: " + playerTwo.getPlayerHealth() + "\n" + playerTwo.getPlayerCombo().toString() );
        return gameInformation;
    }
    
    /**
     * Main method for the project, access point for the java file.
     * @param Array of Strings from Terminal
     */
    public static void main(String[] args) {
        beginGame();
    }
    
    /**
     * Player turn constraints method. This method deals with all the player representation actions.
     * @param player ID
     */
    private static void playerTurn(Player currentPlayer, Player targetPlayer){
        Scanner userInput = new Scanner(System.in);
        String input;
        int intInput;
        Move newMove;
        
        input = ""; // reset
        System.out.println("Player " + currentPlayer.getPlayerID() + " turn: " + "\n" + "1. to add a Move to the combo" + "\n" + "2. to perform the combo");
        while (!input.equals("1") && !input.equals("2")){
            input = userInput.next();
        }
        
        if (input.equals("1")){
            while (!input.equals("a") && !input.equals("b") && !input.equals("c")){
                System.out.println("Input the corresponding letter to add the respective move: " + "\n" + "a. Grapple" + "\n" + "b. Tackle" + "\n" + "c. Disarm");
                input = userInput.nextLine();
            }
                
            if (input.equals("a")){
                newMove = new Grapple();
            }
            else if (input.equals("b")){
                newMove = new Tackle();
            }
                
            else{
                newMove = new Disarm();
            }
                
            boolean passed = false;
            while (!passed){
                try{
                    System.out.println("Choose the position you wish to add the move to. If its the first move, the first position would be position 0. Note that the farthest position you can add the move to is the current size of the combo - 1." + "\n"   + "to add a move to position 0, input 0");
                    intInput = userInput.nextInt();
                    currentPlayer.getPlayerCombo().addMove(newMove, intInput);
                    passed = true;
                }
                catch(IllegalArgumentException e){
                    System.err.println("Invalid position in Combo. A combo consists of sequential moves, so your moves must come in a continuous sequence.");
                    passed = false;
                }
        }
            // Go to second player turn
                
                
        }
            
        else if (input.equals("2")){
            // Perform combo
            targetPlayer.setPlayerHealth(targetPlayer.getPlayerHealth() - currentPlayer.getPlayerCombo().calculateDamageProrated());
            System.out.println("Damage Dealt to player " + targetPlayer.getPlayerID() + ": " + currentPlayer.getPlayerCombo().calculateDamageProrated() + "\n" + "Remaining health: " + targetPlayer.getPlayerHealth());
            
            // Empty moves
            currentPlayer.getPlayerCombo().removeAllMoves();
            
            if (checkGameOver(currentPlayer, targetPlayer) == true){
                System.out.println("Player " + currentPlayer.getPlayerID() + " has won the round! A new round will start shortly.");
                currentPlayer.setPlayerWonRounds(currentPlayer.getPlayerWonRounds() + 1 );
                
                // Cleanup
                targetPlayer.getPlayerCombo().removeAllMoves();
                
            }
            
            
            // Go to second player turn
            
        }
            
        // Cleanup
        userInput.close();
    }

}
