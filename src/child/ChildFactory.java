package child;

import common.Constants;
import input.ChildInputData;


/**
 * Contains the logic of creating children of corresponding age category
 * Based on the Factory design pattern
 */
public final class ChildFactory {

    private ChildFactory() {
        // constructor for checkstyle
    }

    /**
     * Used to assign a child its corresponding age category.
     * it uses an object of ChildInputData and returns an object that
     * inherits Child class, because there are more parameters needed
     * in the flow of the simulation
     * @param child the child that needs to be put in an age category
     * @return
     */
    public static Child createChild(final ChildInputData child) {
        if (child.getAge() < Constants.KID_START_AGE) {
            return new Baby(child);
        } else if (child.getAge()
                        < Constants.TEEN_START_AGE) {
            return new Kid(child);
        } else if (child.getAge()
                        < Constants.YOUNG_ADULT_START_AGE) {
            return new Teen(child);
        } else {
            return new YoungAdult(child);
        }
    }

}
