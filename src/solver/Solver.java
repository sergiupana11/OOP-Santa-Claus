package solver;

import annualchange.AnnualChange;
import child.Child;
import child.ChildUpdate;
import database.Database;
import database.UpdateDatabase;
import enums.Category;
import enums.ElvesType;
import gift.Gift;
import input.GiftInputData;
import input.Input;
import output.AnnualChildren;
import output.ChildOutputData;
import output.OutputData;
import strategy.StrategyFactory;
import visitor.VisitorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that calls all the methods necessary to process the input
 */
public class Solver {

    /**
     *
     * @param input Input object that contains all input data
     * @return OutputData object that contains all output data
     */
    public final OutputData solve(final Input input) {

        // round 0

        // creating a list that contains AnnualChildren objects
        // the list will be populated at the end of every round
        List<AnnualChildren> listOfAnnualChildren = new ArrayList<>();

        // getting budget from input
        Double budget = input.getSantaBudget();

        // populating database with children and determining age category for each child
        UpdateDatabase.addChildren(input.getInitialData().getChildren());

        // populating database with gifts
        Database.getDatabase().getSantaGiftsList()
                .addAll(input.getInitialData().getSantaGiftsList());

        // sorting gifts list by price in ascending order
        Database.getDatabase().getSantaGiftsList()
                .sort(Comparator.comparing(GiftInputData::getPrice));

        // calculating averageScore for each child
        Database.getDatabase().getChildren().forEach(Child::calculateAverageScore);

        // calculating budget unit for each child
        UpdateDatabase.calculateBudgetForEachChild(budget);

        // applying black and pink elves effect
        blackPinkVisit();

        // giving gifts
        giveGifts();

        // applying yellow elf effect
        yellowVisit();

        // creating and building list of all eligible children (age under 19) who received gifts
        List<ChildOutputData> children = new ArrayList<>();
        buildAnnualChildrenList(children);

        // finalizing deep copy of aforementioned list
        AnnualChildren annualChildren = new AnnualChildren(new ArrayList<>(children));
        listOfAnnualChildren.add(annualChildren);

        // subsequent rounds
        for (int i = 0; i < input.getNumberOfYears(); i++) {
            // getting the corresponding annual change from list
            AnnualChange annualChange = input.getAnnualChanges().get(i);

            // updating children data from children updates list,
            // if it exists and is not empty
            if (annualChange.getChildrenUpdates() != null
                    && !annualChange.getChildrenUpdates().isEmpty()) {

                for (ChildUpdate update : annualChange.getChildrenUpdates()) {
                    // selecting the corresponding child by id and applying changes
                    Database.getDatabase().getChildren().stream()
                            .filter(child -> child.getId().equals(update.getId()))
                            .forEach(child -> {
                                        if (update.getNiceScore() != null) {
                                            child.getNiceScoreHistory().add(update.getNiceScore());
                                        }
                                        if (update.getGiftsPreferences() != null) {
                                            UpdateDatabase.updateGiftsPreferences(child,
                                                    update.getGiftsPreferences());
                                        }
                                        if (update.getElf() != null) {
                                            child.setElf(update.getElf());
                                        }
                                    }
                            );
                }
            }

            // modifying budget
            if (annualChange.getNewSantaBudget() != null) {
                budget = annualChange.getNewSantaBudget();
            }

            // adding new gifts in database and sorting by price in ascending order
            if (annualChange.getNewGifts() != null && !annualChange.getNewGifts().isEmpty()) {
                Database.getDatabase().getSantaGiftsList().addAll(annualChange.getNewGifts());
                Database.getDatabase().getSantaGiftsList().sort(Comparator
                        .comparing(GiftInputData::getPrice));
            }

            // updating children age and removing those who became YoungAdults
            UpdateDatabase.updateChildrenAge();

            // adding new children in database
            if (annualChange.getNewChildren() != null && !annualChange.getNewChildren().isEmpty()) {
                UpdateDatabase.addChildren(annualChange.getNewChildren());
            }

            // calculating averageScore for each child
            Database.getDatabase().getChildren().forEach(Child::calculateAverageScore);

            // calculating allocated budget for each child
            UpdateDatabase.calculateBudgetForEachChild(budget);

            // applying black and pink elf effect
            blackPinkVisit();

            // applying strategy for giving gifts
            (StrategyFactory.createStrategy(annualChange.getStrategy())).sortChildrenList();

            // giving gifts in the order arranged by selected strategy
            giveGifts();

            // apply yellow elf effect
            yellowVisit();

            // building
            buildAnnualChildrenList(children);
            annualChildren = new AnnualChildren(new ArrayList<>(children));
            listOfAnnualChildren.add(annualChildren);

        }

        Database.getDatabase().clear();

        return new OutputData(listOfAnnualChildren);
    }

    // applies the effects of the black or the pink elf, depending on each child
    private void blackPinkVisit() {
        for (Child child : Database.getDatabase().getChildren()) {
            if (child.getElf().equals(ElvesType.BLACK) || child.getElf().equals(ElvesType.PINK)) {
                child.accept(VisitorFactory.createVisitor(child.getElf()));
            }
        }
    }

    // applies the effect of yellow elf, for the children it is assigned to
    private void yellowVisit() {
        for (Child child : Database.getDatabase().getChildren()) {
            if (child.getElf().equals(ElvesType.YELLOW)) {
                child.accept(VisitorFactory.createVisitor(child.getElf()));
            }
        }
    }

    // It gives gifts to children, if the following conditions are met (the gifts list
    // is ordered by price, in ascending order):
    // - a gift from the respective category exists
    // - there is at least one gift of that gift type (if not, search for the next one)
    // - the child's budget is greater that the gift's price
    // - a child can only receive one gift from a given category
    // After these steps, the budget is decreased by the price of the gift and
    // the number of gifts of that specific type is decreased by 1.
    // The loop is started again until there aren't any eligible gifts found or
    // until there is no money left.
    private void giveGifts() {
        for (Child child : Database.getDatabase().getChildren()) {
            if (!child.getReceivedGifts().isEmpty()) {
                child.getReceivedGifts().clear();
            }
            Double budget = child.getAssignedBudget();
            if (budget == 0.0) {
                continue;
            }
            for (Category preference : child.getGiftsPreferences()) {
                for (GiftInputData gift : Database.getDatabase().getSantaGiftsList()) {
                    if (gift.getCategory().equals(preference)) {
                        if (budget >= gift.getPrice()) {
                            if (gift.getQuantity() > 0) {
                                child.getReceivedGifts().add(new Gift(gift.getProductName(),
                                        gift.getPrice(), gift.getCategory()));
                                budget -= gift.getPrice();
                                gift.setQuantity(gift.getQuantity() - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // Given a children list, build ChildrenOutputObjects by deep copying them from the Database
    // and add them to the given list.
    // After the list was built, it is sorted by id, in ascending order
    private void buildAnnualChildrenList(final List<ChildOutputData> children) {
        children.clear();
        for (Child child : Database.getDatabase().getChildren()) {
            children.add(new ChildOutputData(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    child.getGiftsPreferences(), child.getAverageScore(),
                    child.getNiceScoreHistory(), child.getAssignedBudget(),
                    child.getReceivedGifts()));
        }
        children.sort((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId() < o2.getId()) {
                return -1;
            }
            return 0;
        });
    }

}
