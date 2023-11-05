

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MoveTest. Tests the functionality of the Move class.
 *
 * @author  Faycal Kilali
 * @version V1.0
 */

public class MoveTest
{
    private Move move1;
    private Move move2;
    /**
     * Default constructor for test class MoveTest
     */
    public MoveTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        move1 = new Move(20); // Use a sample damage value
        move2 = new Move(25);
        move1.setNext(move2);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        move1 = null;
        move2 = null;
    }
    
        @Test
    public void testGetNext() {
        // Test for accessing the second move from the first move
        assert(move1.getNext() == move2);
        
    }

    @Test
    public void testSetNext() {
        // Test mutating the next reference
        move2.setNext(move1);
        assert (move2.getNext() == move1);
    }

    @Test
    public void testGetData() {
        // Test accessing the data inside a Move object
        assert(20 == move1.getData());
    }

    @Test
    public void testGetDamage() {
        // Test getting the damage of the move object
        assert(20 == move1.getData());
    }

    @Test
    public void testSetDamage() {
        // Test setting the damage of the move object
        move2.setDamage(200);
        int testHolder = 200;
        assert(testHolder == move2.getDamage());
    }
}
