package strategy;

import child.Child;
import database.Database;
import enums.Cities;
import input.GiftInputData;

import java.util.Comparator;

public class SortByCityNiceScore implements Strategy {

    @Override
    public final void sortChildrenList() {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
        for (Cities city : Cities.values()) {
            Double averageScore = 0.0;
            int number = 0;
            for (Child child : Database.getDatabase().getChildren()) {
                if (child.getCity().equals(city)) {
                    averageScore += child.getAverageScore();
                    number++;
                }
            }
            averageScore /= number;
            city.setAverageScore(averageScore);
        }

        for (Child child : Database.getDatabase().getChildren()) {
            child.setAverageScoreOfCity(child.getCity().getAverageScore());
            child.setCityName(child.getCity().getValue());
        }

        Database.getDatabase().getChildren().sort(Comparator
                .comparing(Child::getAverageScoreOfCity).reversed()
                .thenComparing(Child::getCityName));

        Database.getDatabase().getSantaGiftsList()
                .sort(Comparator.comparing(GiftInputData::getPrice));
    }
}

