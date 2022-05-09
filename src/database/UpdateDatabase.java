package database;

import child.Child;
import child.ChildFactory;
import child.Kid;
import child.Teen;
import child.YoungAdult;
import common.Constants;
import enums.Category;
import input.ChildInputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * class that updates the information held in the Database
 */
public final class UpdateDatabase {

    private UpdateDatabase() {
        //constructor for checkstyle
    }

    /**
     * calculates and assigns the budget of each child
     * it uses the following formulas:
     * budgetUnit = santaBudget / niceScoresSum, where niceScoresSum is the sum of
     * every child's niceScore.
     *
     * assignedBudget = budgetUnit * niceScore - this formula is applied for each child
     * @param santaBudget total budget
     */
    public static void calculateBudgetForEachChild(final Double santaBudget) {
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));
        Double niceScoresSum = 0.0;
        for (Child child : Database.getDatabase().getChildren()) {
            niceScoresSum += child.getAverageScore();
        }

        Double budgetUnit = santaBudget / niceScoresSum;

        for (Child child : Database.getDatabase().getChildren()) {
            Double assigned = child.getAverageScore() * budgetUnit;
            child.setAssignedBudget(assigned);
        }
    }

    /**
     * for given child, updates their gift preferences list, as following:
     * if their current list doesn't contain any of the new preferences, they are added
     * at the beginning of the child's preferences list
     *
     * if their current list contains any preference that is found in the new preferences
     * list, that preference is removed and added again at the beginning
     * @param child the Child object that we want to modify
     * @param preferences the new preferences that we want to add
     */
    public static void updateGiftsPreferences(final Child child, final List<Category> preferences) {
        child.getGiftsPreferences().removeAll(preferences);
        Collections.reverse(preferences);
        for (Category preference : preferences) {
            if (!child.getGiftsPreferences().contains(preference)) {
                child.getGiftsPreferences().add(0, preference);
            } else {
                child.getGiftsPreferences().remove(preference);
                child.getGiftsPreferences().add(0, preference);
            }
        }
    }

    /**
     * adds children from an input list (contains ChildInputData objects) and converts them
     * to the corresponding subclass of class Child, depending on their age
     * @param children
     */
    public static void addChildren(final List<ChildInputData> children) {
        for (ChildInputData child : children) {
            if (ChildFactory.createChild(child) instanceof YoungAdult) {
                continue;
            }
            Database.getDatabase().addChild(ChildFactory.createChild(child));
        }
    }

    /**
     * ages all children in the Database by one year and checks if their age corresponds to
     * a new age category
     * if it does, there are two options:
     *  1. the child either became a Kid or a Teen, in which case it will have its age category
     *      updated and the rest of the information will be kept the same.
     *  2. the child became a YoungAdult, in which case it will be removed from the Database.
     *      this is not possible in a for each loop, so it will be added to a list of children
     *      to remove from the database.
     *      after the iteration through the Database is finished, all YoungAdults will be removed
     */
    public static void updateChildrenAge() {
        List<Child> toRemove = new ArrayList<>();

        for (Child child : Database.getDatabase().getChildren()) {
            child.incrementAge();
            if (child.getAge().equals(Constants.KID_START_AGE)) {
                int index = Database.getDatabase().getChildren().indexOf(child);
                Database.getDatabase().getChildren().set(index, modifyAgeCategory(child));
            } else if (child.getAge().equals(Constants.TEEN_START_AGE)) {
                int index = Database.getDatabase().getChildren().indexOf(child);
                Database.getDatabase().getChildren().set(index, modifyAgeCategory(child));
            } else if (child.getAge().equals(Constants.YOUNG_ADULT_START_AGE)) {
                toRemove.add(child);
            }
        }

        Database.getDatabase().getChildren().removeAll(toRemove);
    }

    /**
     * function called in updateChildrenAge function to modify the age category
     * of given child
     * @param child child that we want to promote to next age category
     * @return child of new age category
     */
    private static Child modifyAgeCategory(final Child child) {
        if (child.getAge().equals(Constants.KID_START_AGE)) {
            return new Kid(child);
        } else if (child.getAge().equals(Constants.TEEN_START_AGE)) {
            return new Teen(child);
        }
        return null;
    }

}
