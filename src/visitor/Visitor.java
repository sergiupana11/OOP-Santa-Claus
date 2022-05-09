package visitor;

import child.Child;

/**
 * Helps implement the Visitor design pattern
 * Contains a function that is implemented by all the visitors (the four elves)
 */
public interface Visitor {

    /**
     * Applies the effects of the visitor
     * @param child the child that we want to apply the effects on
     */
    void visit(Child child);
}
