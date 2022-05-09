package strategy;

import child.Child;
import database.Database;

import java.util.Comparator;

public class SortByNiceScore implements Strategy {
    @Override
    public final void sortChildrenList() {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getAverageScore)
                .reversed().thenComparing(Child::getId));
    }
}
