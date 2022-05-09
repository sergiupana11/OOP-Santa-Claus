package child;

import common.Constants;
import enums.Cities;
import input.ChildInputData;

public class Kid extends Child {
    public Kid(final Integer id, final String lastName, final String firstName, final Integer age,
               final Cities city) {
        super(id, lastName, firstName, age, city);
    }

    public Kid(final ChildInputData child) {
        super(child);
    }

    public Kid(final Child child) {
        super(child);
    }

    @Override
    public final void calculateAverageScore() {
        double averageScore = 0.0;
        for (Double score : getNiceScoreHistory()) {
            averageScore += score;
        }

        averageScore /= getNiceScoreHistory().size();

        averageScore += averageScore * getNiceScoreBonus() / Constants.PERCENT;

        if (averageScore > Constants.MAXIMUM_NICE_SCORE) {
            averageScore = Constants.MAXIMUM_NICE_SCORE;
        }

        setAverageScore(averageScore);
    }

}
