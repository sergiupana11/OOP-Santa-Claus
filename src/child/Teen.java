package child;

import common.Constants;
import input.ChildInputData;

public class Teen extends Child {

    public Teen(final ChildInputData child) {
        super(child);
    }

    public Teen(final Child child) {
        super(child);
    }

    @Override
    public final void calculateAverageScore() {
        double averageScore = 0.0;
        int totalWeight = 0;
        for (int i = 0; i < getNiceScoreHistory().size(); i++) {
            averageScore += getNiceScoreHistory().get(i) * (i + 1);
            totalWeight += (i + 1);
        }
        averageScore /= totalWeight;

        averageScore += averageScore * getNiceScoreBonus() / Constants.PERCENT;

        if (averageScore > Constants.MAXIMUM_NICE_SCORE) {
            averageScore = Constants.MAXIMUM_NICE_SCORE;
        }

        setAverageScore(averageScore);
    }
}
