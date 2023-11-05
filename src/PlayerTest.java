

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PlayerTest. Tests the functionality of the Player class.
 *
 * @author  Faycal Kilali
 * @version V1.0
 */
public class PlayerTest
{
    private Player player;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        player = new Player(100);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        player = null;
    }
    
        @Test
    public void testGetPlayerID() {
        System.out.println(player.getPlayerID());
        assert (3 == player.getPlayerID()); // third player made, due to the main method running before (which initializes beginGame() which intiializes 2 players before this code executes
        
    }

    @Test
    public void testGetPlayerHealth() {
        // Test getting the player health
        assert(100 == player.getPlayerHealth());
        
    }

    @Test
    public void testSetPlayerHealth() {
        // Test setting the player health
        player.setPlayerHealth(1000);
        assert (1000 == player.getPlayerHealth());
    }

    @Test
    public void testGetPlayerCombo() {
        // Test getting the player combo
        assert (player.getPlayerCombo().getSize() == 0);
    }

    @Test
    public void testGetPlayerWonRounds() {
        // Test getting the number of won rounds
        assert (player.getPlayerWonRounds() == 0);
    }

    @Test
    public void testSetPlayerWonRounds() {
        // Test setting the number of won rounds
        player.setPlayerWonRounds(1);
        assert (player.getPlayerWonRounds() == 1);
    }
}
