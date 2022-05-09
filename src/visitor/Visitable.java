package visitor;

/**
 * Contains the functions that accepts the visitor's visit.
 * It is implemented in the Child class
 */
public interface Visitable {

    /**
     * Function that accepts the visit from the visitor and applies its effect.
     * It is implemented in the Child class
     * @param v
     */
    void accept(Visitor v);
}
