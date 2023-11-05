
/*
 * ComboADT is an interface that specifies the methods
 * that your Combo class should be able to perform.
 * 
 * It is a rebrand of the LinkedList with a few
 * extra flavors mixed in.
 * 
 * In more general situations we would likely have
 * the Combo class extend an existing LinkedList
 * implementation and add the additional logic
 * there, but for this activity we will do it
 * in one place. That is also why we have eschewed
 * the raw type for this focused activity.
 * 
 * Your implementation of Combo should be styled as
 * a LinkedList and not use any other data structures
 * to manage the Move objects within the Combo context.
 * 
 * Unlike the in-class example, the 'Nodes' in this
 * case are not kept private within the Combo class
 * but are an external public class called "Move". This
 * gives you greater control over potential architectural
 * choices with that class.
 * 
 * Your approach to this activity may make you not need
 * to use some of these methods; you should implement and
 * test them all anyways. They are present to ensure that
 * most approaches to this activity are capable using this
 * interface as a constraint on the Combo class.
 * 
 * There should be no other public methods in the Combo
 * class, but local/private methods are permitted.
 * 
 */

public interface ComboADT {

    /*
     * This method should calculate the total
     * damage of this Combo taking the proration
     * of each move into account.
     * 
     */
    
    public abstract int calculateDamageProrated();
    
    /*
     * This method should calculate the total damage
     * of this Combo without taking the proration
     * of each move into account.
     * 
     * This exists for testing purposes primarily.
     * 
     */
    
    public abstract int calculateDamageNonProrated();
    
    /*
     * This method will add the provided Move object
     * to the end of the Combo.
     * 
     */
    
    public abstract void addMoveToEnd(Move move);
    
    /*
     * This method will add the provided Move object
     * to the start of the Combo.
     * 
     */
    
    public abstract void addMoveToStart(Move move);
    
    /*
     * This method will add the provided Move object
     * to the designated numeric position in the list.
     * 
     * If the position is not legal to add to, an exception
     * should be thrown.
     * 
     */
    
    public abstract void addMove(Move move, int position);
    
    /*
     * This method will remove the Move object at the
     * designated numeric position.
     * 
     * If the position is not legal to remove from, an
     * exception should be thrown.
     * 
     */
    
    public abstract Move removeMove(int position);
    
    /*
     * This method will remove the Move object currently
     * at the 'front' of the Combo list.
     * 
     * If no Move exists at that position, an exception
     * should be thrown.
     * 
     */
    
    public abstract Move removeMoveFromFront();
    
    /*
     * This method will return the number of Move
     * objects currently in the Combo list.
     * 
     */
    
    public abstract int getSize();
    
    /*
     * This method will remove all Moves from the
     * Combo list.
     * 
     */
    
    public abstract void removeAllMoves();
    
    /*
     * This method should produce a textual representation
     * of the Move objects stored inside the Combo.
     * 
     * This method is present to help you in testing
     * to provide a way to ensure the contents of the
     * Combo are as you expect.
     * 
     * You may also consider ways of labeling unique
     * Move objects for an interesting textual representation.
     * 
     */
    
    public abstract String toString();
    
}
