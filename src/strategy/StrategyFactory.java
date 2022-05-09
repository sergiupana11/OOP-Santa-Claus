package strategy;

import enums.CityStrategyEnum;

public final class StrategyFactory {

    private StrategyFactory() {
        // constructor for checkstyle
    }

    /**
     * Used to create objects of the given strategy type to sort the children list
     * @param strategy strategy which is wanted to be executed
     */
    public static Strategy createStrategy(final CityStrategyEnum strategy) {
        return switch (strategy) {
            case NICE_SCORE_CITY -> new SortByCityNiceScore();
            case ID -> new SortByID();
            case NICE_SCORE -> new SortByNiceScore();
        };
    }
}
