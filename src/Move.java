
    public class Move{

    private Integer damage;
    private Move reference; // Reference to the next move


    /**
     * Constructor for objects of class Move with null reference
     * @param inDamage the damage the move performs
     */
     public Move(Integer inDamage)
    {
        damage = inDamage;
        reference = null;
    }



    /**
     * Accessor method for the next Move in the Combo.
     * @return reference the reference of the next Move in the Combo.
     */
    public Move getNext(){
     return reference;   
    }
    
    /**
     * Setter method for the next Move in the combo
     * @param inMove sets the next move in the combo
     */

    public void setNext(Move inMove){
     reference = inMove;   
    }

    /**
     * Accessor method for the current damage
     */
    public Integer getData(){
        return damage;
    }
    
     /**
     * Accessor method for the current damage
     */
    public Integer getDamage(){
        return getData();
    }
    
    /**
     * Setter method for the damage
     */
    public void setDamage(Integer inDamage){
        damage = inDamage;
    }

}