package strategy;

/**
 * Contains a function to sort children list
 * It is implemented in all classes that implement it
 * Used for Strategy Design pattern
 */
public interface Strategy {

    /**
     * Function so sort children list by given criteria
     */
    void sortChildrenList();
}
