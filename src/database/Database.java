package database;

import child.Child;
import input.GiftInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains the list that will be processed during the simulation
 */
public final class Database {
    private static Database database = null;
    private final List<Child> children;
    private final List<GiftInputData> santaGiftsList;

    private Database() {
        children = new ArrayList<>();
        santaGiftsList = new ArrayList<>();
    }

    /**
     * Ensures that there is only one instance of the Database created and returns
     * it to process data (Singleton design pattern)
     * @return database
     */
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    /**
     * Clears the database.
     * This is needed because otherwise, the following tests will append their data
     * to the existing data and the results will not be the ones that are expected.
     */
    public void clear() {
        children.clear();
        santaGiftsList.clear();
        database = null;
    }

    public List<Child> getChildren() {
        return children;
    }

    /**
     * Adds one child to the Database
     */
    public void addChild(final Child child) {
        children.add(child);
    }

    public List<GiftInputData> getSantaGiftsList() {
        return santaGiftsList;
    }
}
