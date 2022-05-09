package strategy;

import child.Child;
import database.Database;

import java.util.Comparator;

public class SortByID implements Strategy {
    @Override
    public final void sortChildrenList() {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
    }
}
