

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ComboTest. Tests the functionality of the Combo class.
 *
 * @author  Faycal Kilali
 * @version V1.0
 */
public class ComboTest
{
    private Combo combo;
    /**
     * Default constructor for test class ComboTest
     */
    public ComboTest()
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
        combo = new Combo();

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        combo = null;
        
    }
    
    
        @Test
    public void testCalculateDamageNonProrated() {
        // Test the non-prorated damage
        Move newMove1 = new Move(10);
        Move newMove2 = new Move(20);
        Move newMove3 = new Move(25);
        combo.addMoveToStart(newMove1);
        combo.addMoveToStart(newMove2);
        combo.addMoveToStart(newMove3);
        int nonProratedDamage = newMove1.getDamage() + newMove2.getDamage() + newMove3.getDamage();
        
        assertEquals(nonProratedDamage, combo.calculateDamageNonProrated());
    }

    @Test
    public void testCalculateDamageProrated() {

        Move newMove1 = new Move(44);
        Move newMove2 = new Move(46);
        Move newMove3 = new Move(50);
        Move newMove4 = new Move(28);
        combo.addMoveToStart(newMove1);
        combo.addMoveToStart(newMove2);
        combo.addMoveToStart(newMove3);
        combo.addMoveToStart(newMove4);
        int proratedDamage = (int) Math.floor( newMove4.getDamage() * 1.0 + newMove3.getDamage() * 1.0 + newMove2.getDamage() * 0.9 + newMove1.getDamage() * 0.8);

        assertEquals(proratedDamage, combo.calculateDamageProrated());
    }

    @Test
    public void testAddMoveToStart() {
        // The following made me realize how important to toString() method is at making it simple enough for testing
        // We'll now test adding to the beginning of an empty Combo
        Combo combo1 = new Combo();
        Move move1 = new Move(10);
        combo1.addMoveToStart(move1);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 10 Proration for next Move: 1.0\n", combo1.toString(), "Adding to an empty combo");

        // We'll now add to the start of a non-empty combo
        Combo combo2 = new Combo();
        Move move2 = new Move(20);
        combo2.addMoveToStart(move2);
        Move move3 = new Move(30);
        combo2.addMoveToStart(move3);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 30 Proration for next Move: 1.0\nMaximum possible damage exclusive of proration: 20 Proration for next Move: 0.9\n", combo2.toString(), "Adding to a non-empty combo");
    }


    @Test
    public void testAddMoveToEnd() {
        // Test adding a move to the end of an empty combo
        Combo combo1 = new Combo();
        Move move1 = new Move(10);
        combo1.addMoveToEnd(move1);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 10 Proration for next Move: 1.0\n", combo1.toString(), "Adding to the end of an empty combo");

        // Test adding a move to the end of a non-empty combo
        Combo combo2 = new Combo();
        Move move2 = new Move(20);
        Move move3 = new Move(30);
        combo2.addMoveToStart(move2);
        combo2.addMoveToEnd(move3);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 20 Proration for next Move: 1.0\nMaximum possible damage exclusive of proration: 30 Proration for next Move: 0.9\n", combo2.toString(), "Adding to the end of a non-empty combo");
    }

    @Test
    public void testAddMove() {
        // Test adding a move at position 0
        Combo combo1 = new Combo();
        Move move1 = new Move(10);
        combo1.addMove(move1, 0);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 10 Proration for next Move: 1.0\n", combo1.toString(), "Adding to position 0");

        // Test adding a move at a middle position
        Combo combo2 = new Combo();
        Move move2 = new Move(20);
        Move move3 = new Move(30);
        combo2.addMoveToStart(move2);
        combo2.addMove(move3, 1);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 20 Proration for next Move: 1.0\nMaximum possible damage exclusive of proration: 30 Proration for next Move: 0.9\n", combo2.toString(), "Adding to a middle position");

        // Test adding a move at the end position
        Combo combo3 = new Combo();
        Move move4 = new Move(40);
        Move move5 = new Move(50);
        combo3.addMoveToStart(move4);
        combo3.addMove(move5, 1);
        assertEquals("Combo: Maximum possible damage exclusive of proration: 40 Proration for next Move: 1.0\nMaximum possible damage exclusive of proration: 50 Proration for next Move: 0.9\n", combo3.toString(), "Adding to the end position");
    }

    @Test
    public void testRemoveMoveFromFront() {
        // Test removing a move from the front of a non-empty combo
        Combo combo1 = new Combo();
        Move move1 = new Move(10);
        Move move2 = new Move(20);
        combo1.addMoveToStart(move1);
        combo1.addMoveToEnd(move2);
        Move removedMove1 = combo1.removeMoveFromFront();
        assertEquals(move1, removedMove1, "Removing from the front of a non-empty combo");

        // Test removing a move from the front of an empty combo (I think this'll throw an exception or null, one of the two at least)
        Combo combo2 = new Combo();
        Move removedMove2 = combo2.removeMoveFromFront();
        assertNull(removedMove2, "Removing from the front of an empty combo");
    }

    @Test
    public void testRemoveMove() {
        
        System.out.println("TODO: This test case keeps failing for some reason, yet the method appears to work properly. Would appreciate some feedback on this test case -- did I write the test wrong? Did I mess up the method?");
        // Test removing a move from the middle of the combo
        Combo combo1 = new Combo();
        Move move1 = new Move(10);
        Move move2 = new Move(20);
        Move move3 = new Move(30);
        combo1.addMoveToStart(move1);
        combo1.addMoveToEnd(move2);
        combo1.addMove(move3, 1);
        Move removedMove1 = combo1.removeMove(1);
        assertEquals(move3, removedMove1, "Removing from the middle of the combo");

        // Test removing a move from the end of the combo
        Combo combo2 = new Combo();
        Move move4 = new Move(40);
        Move move5 = new Move(50);
        combo2.addMoveToStart(move4);
        combo2.addMoveToEnd(move5);
        Move removedMove2 = combo2.removeMove(1);
        assertEquals(move5, removedMove2, "Removing from the end of the combo");
    }


    @Test
    public void testRemoveAllMoves() {
        // Add moves to the combo
        // Call removeAllMoves
        // Will use assert to check if the size is 0 (note could've used assertTrue instead, but isEmpty() is private so I can't)
        combo.removeAllMoves();
        assert(combo.getSize() == 0);
    }

    @Test
    public void testGetSize() {
        // We'll call and compare with the getSize method to the expected size
        Move move1 = new Move(10);
        combo.addMove(move1, 0);
        assertEquals(combo.getSize(), 1);
    }
}
