package child;

import common.Constants;
import input.ChildInputData;

public class Baby extends Child {

    public Baby(final ChildInputData child) {
        super(child);
    }

    @Override
    public final void calculateAverageScore() {
        setAverageScore(Constants.BABY_NICE_SCORE);
    }
}
