/**
* The Combo class is an implementation of the ComboADT Interface as a Linked List.
* The Head is at the start of the Linked List, we use it to traverse the entire LinkedList for now.
* The Moves behave like nodes with no internal data object reference -- instead -- they hold the move's damage
*
* @author Faycal Kilali
* @version 1.0
* 
*/
public class Combo implements ComboADT
{
    private Move head; // Recall that this variable actually points to the head move
    private int position;
    
    
    /**
     * Constructor for our Combo list
     */
    
    public Combo (){
        head = null;
    }

    /**
     * Calculates the total damage without taking prortation to account
     * @return the total damage without proration
     */
    public int calculateDamageNonProrated(){
        int curDamage = 0;
        
        Move currMove = head;
        while (currMove != null){
            curDamage = (int) Math.floor(curDamage + currMove.getDamage());
            currMove = currMove.getNext();
        }
        return curDamage;
    }
    
    /**
     * Calculates the total damage with proration
     * @return the total damagew with proration
     */
    public int calculateDamageProrated(){
        int curDamage = 0;
        Double proration = 1.0;
        Move nextMove = null;

        
        Move currMove = head;
        if (currMove != null){
            nextMove = currMove.getNext();
        }
        
        // First Move seperate case
        if (currMove != null){
            curDamage = curDamage + currMove.getDamage(); // 100% proration so we can skip the multiplication
        }
        
        while (currMove != null){
            
            if ((nextMove != null) && (proration > 0)){
                curDamage += nextMove.getDamage() * proration;
            }
            proration -= 0.1;
            currMove = currMove.getNext();
            if (currMove != null){
                nextMove = currMove.getNext();
            }
        }
        return (int) Math.floor(curDamage);
    }

    
    /**
     * This adds a new move to the front of the list (this move becomes the new head move).
     * So, the head move gets pushed to the right, and this gets placed in its previous spot (so this becomes the new move at the start of the list)
     * @throws NullPointerException if inMove is null. The Exception should be caught and the method re-called with a non-null object.
     */
    public void addMoveToStart(Move inMove) throws NullPointerException
    { 
        if (inMove == null){
          throw new NullPointerException("Parameterized object is null");
        }
        if(isEmpty()){
            head = inMove;
        }
        else{
            inMove.setNext(head);
            head = inMove;
        }
    }

    /**
     * Adds a move to the tail of the linked list
     * @param object reference of the new tail
     * @throws NullPointerException if inObject is null. The Exception should be caught and the method re-called with a non-null object.
     */
    @Override
    public void addMoveToEnd(Move inMove)throws NullPointerException{
        if (inMove == null){
          throw new NullPointerException("Parameterized object is null");
        }
        
        if (getSize() == 0){
            addMoveToStart(inMove);
            return;
        }
        Move currMove = traverseLinkedListToReference(null);
        //Move insertMove = new Move(inObject);
        Move insertMove = inMove;
        currMove.setNext(insertMove);
    }


    /**
     * Adds a move after a particular move (usually between two moves, but not necessarily between two moves because it could be the last move)
     * @param inObject, inReference
     * @throws NullPointerException if inObject is null or inReference is null. The Exception should be caught and the method re-called with a non-null object. IllegalArgumentException if the position is illegal.
     */
    @Override
    public void addMove(Move inMove, int inPosition) throws NullPointerException, IllegalArgumentException
    {
                    
        if (inPosition == 0){
            addMoveToStart(inMove);
            return;
        }
        
        if (inPosition == getSize()){
            addMoveToEnd(inMove);
            return;
        }
        
        
        if (inMove == null){
          throw new NullPointerException("Parameterized object is null");
        }
        
        if ( (inPosition > getSize()) || (inPosition < 0) ){
            throw new IllegalArgumentException("Illegal position in Data Structure");
        }
        
        Move currMove = traverseLinkedListByPosition(inPosition); 
        Move insertMove = inMove; 
        if (  (currMove.getNext() != null) ){
            insertMove.setNext(currMove.getNext()); // Sets the correct reference for the move we are adding so the chain isn't broken
            }
        currMove.setNext(insertMove); // Updates the previous move's pointer to the newly added move
    }

    /**
     * Helper method to traverse a Linked List up to the position of inPosition
     * @Param inPosition the position starting from the head Move
     * @return Move, returns the move at the inPosition'th position.
     */
    private Move traverseLinkedListByPosition(int inPosition) throws IllegalArgumentException{
        
        // No position when LL is empty
        if (isEmpty()){
            throw new IllegalStateException("Linked list is empty.");
        }
        
        if ( (inPosition > getSize()) || (inPosition < 0) ){
            throw new IllegalArgumentException("Illegal position in Data Structure. Position must be positive and must be smaller than size of Data Structure. Maximal position is " + getSize() + ", received" + inPosition);
        }
        
        int curPosition = 0;
        Move currMove = head;
        while (currMove != null){
            if (curPosition == inPosition){
                return currMove;
            }
            currMove = currMove.getNext();
            curPosition++;
        }
        // The following line should not be reached if the position is legal
        throw new IllegalStateException("Unexpected error in traverseLinkedListByPosition");
    }
    
    /**
     * Helper method to traverse a Linked List up to the Move before inReference
     * @param inReference
     * @return Move, returns the node before inReference
     */
    private Move traverseLinkedListToReference(Move inReference) throws NullPointerException{
        if (isEmpty() == true){
            throw new NullPointerException();
        }
        Move currMove = head;
        while (currMove.getNext() != inReference){
            currMove = currMove.getNext();
        }
        return currMove;
    }
 

    /**
     * Removes the whole first move from the Linked List
     * This accesses the referenced move's reference, hence traversing exactly two moves forward from the head move in order to connect the move before the inReference move with the move that comes after inReference.
     * This should not return a null value. Rather, the exception EmptyListException should be thrown. The user can recover from that by ensuring they are removing from a non-empty list
     * @return the removed move, or null if no move to be removed
     */
    
    @Override
    public Move removeMoveFromFront(){
       if (isEmpty() == false){
        Move currMove = head;
        head = head.getNext();
        
        // Accessing the referenced move's reference (traversing)
        return currMove;
        }

        return null;
    }

    /**
     * Removes the Move at position inPosition from the Combo list.
     * @param inPosition the position of the Move to be removed
     * @return the removed move
     */
    @Override
    public Move removeMove(int inPosition){
        Move locatedMove = traverseLinkedListByPosition(inPosition);
        Move afterLocatedMove = locatedMove.getNext();
        Move beforeLocatedMove = traverseLinkedListToReference(locatedMove);
        beforeLocatedMove.setNext(afterLocatedMove); // orphans locatedMove
        return locatedMove;
    }
    
    /**
     * Removes all the Moves from the combo
     */
    @Override
    public void removeAllMoves(){
        head = null;

    }



    @Override
    /**
     * O(1) if we increment and decrement based on the other methods, otherwise O(n) if we traverse this way
     */
    public int getSize(){
        if(isEmpty()){
            return 0;
        }

        int size = 0;
        Move currentMove = head;
        while(currentMove !=null){
            currentMove = currentMove.getNext();
            size++;
        }
        return size;


    }

    private boolean isEmpty(){
        if (head == null){
            return true;
        }

        return false;
    }

        /**
         * Original method from the COMP-2611 LAB we had for LinkedList as starter code by Ada Clevinger
         * Edits by Faycal Kilali
         * @return returns the textual string representation of the combo
         */    
        @Override
        public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Combo: ");
        if(head != null){
            Move curr = head;
            Double proration = 1.0;
            while(curr != null){
                //sb.append(curr.getData() + ", ");
                //sb.append(curr.toString() + ", "); // We care about the Move object for textual representation
                // Maybe this'll work better                
                sb.append("Maximum possible damage exclusive of proration: " + curr.getDamage() + " Proration for next Move: " + proration + "\n");
                if (proration > 0){
                    proration -= 0.1;
                }
                curr = curr.getNext();
            }
        }
        sb.append("");

        return sb.toString();
    }
    
    
    /**
     * Method for printing from the toString method
     */
    private void showCombo(){
        System.out.println(toString());
    }

 

 

}